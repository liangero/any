package com.any.pub;

import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * HttpPutDeleteFormContentFilter 支持body传参
 * Created by avaio on 2017/1/16.
 */
public class HttpPutDeleteFormContentFilter extends OncePerRequestFilter {
    private final FormHttpMessageConverter formConverter = new AllEncompassingFormHttpMessageConverter();

    public HttpPutDeleteFormContentFilter() {
    }

    public void setCharset(Charset charset) {
        this.formConverter.setCharset(charset);
    }

    protected void doFilterInternal(final HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (("PUT".equals(request.getMethod()) || "PATCH".equals(request.getMethod()) || "DELETE".equals(request.getMethod())) && this.isFormContentType(request)) {
            ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request) {
                public InputStream getBody() throws IOException {
                    return request.getInputStream();
                }
            };
            MultiValueMap<String, String> formParameters = this.formConverter.read(null, inputMessage);
            HttpPutDeleteFormContentFilter.HttpPutFormContentRequestWrapper wrapper = new HttpPutDeleteFormContentFilter.HttpPutFormContentRequestWrapper(request, formParameters);
            filterChain.doFilter(wrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }

    private boolean isFormContentType(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType != null) {
            try {
                MediaType ex = MediaType.parseMediaType(contentType);
                return MediaType.APPLICATION_FORM_URLENCODED.includes(ex);
            } catch (IllegalArgumentException var4) {
                return false;
            }
        } else {
            return false;
        }
    }

    private static class HttpPutFormContentRequestWrapper extends HttpServletRequestWrapper {
        private MultiValueMap<String, String> formParameters;

        HttpPutFormContentRequestWrapper(HttpServletRequest request, MultiValueMap<String, String> parameters) {
            super(request);
            this.formParameters = parameters != null ? parameters : new LinkedMultiValueMap();
        }

        public String getParameter(String name) {
            String queryStringValue = super.getParameter(name);
            String formValue = this.formParameters.getFirst(name);
            return queryStringValue != null ? queryStringValue : formValue;
        }

        public Map<String, String[]> getParameterMap() {
            LinkedHashMap<String, String[]> result = new LinkedHashMap<>();
            Enumeration names = this.getParameterNames();

            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                result.put(name, this.getParameterValues(name));
            }

            return result;
        }

        public Enumeration<String> getParameterNames() {
            LinkedHashSet<String> names = new LinkedHashSet<>();
            names.addAll(Collections.list(super.getParameterNames()));
            names.addAll(this.formParameters.keySet());
            return Collections.enumeration(names);
        }

        public String[] getParameterValues(String name) {
            String[] queryStringValues = super.getParameterValues(name);
            List<String> formValues = this.formParameters.get(name);
            if (formValues == null) {
                return queryStringValues;
            } else if (queryStringValues == null) {
                return formValues.toArray(new String[formValues.size()]);
            } else {
                ArrayList<String> result = new ArrayList<>(queryStringValues.length + formValues.size());
                result.addAll(Arrays.asList(queryStringValues));
                result.addAll(formValues);
                return result.toArray(new String[result.size()]);
            }
        }
    }
}

package com.any.pub;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 重写springmvc argument resolvers 以实现直接解析前端数据
 * Created by avaio on 2016/12/20.
 */
public class ArgumentResolvers extends ServletModelAttributeMethodProcessor {
    public ArgumentResolvers() {
        super(true);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
        binder.bind(this.ansPv(servletRequest));
    }

    private MutablePropertyValues ansPv(ServletRequest request) {
        MutablePropertyValues mpvs = new MutablePropertyValues();
        Map<String, Object> parametersStartingWith = WebUtils.getParametersStartingWith(request, null);
        for (Map.Entry<String, Object> pv : parametersStartingWith.entrySet()) {
            mpvs.add(this.convertKey(pv.getKey()), pv.getValue());
        }
        return mpvs;
    }

    private String convertKey(String key) {
        Pattern compile = Pattern.compile("\\[[^0-9\\]]*\\]");
        Matcher matcher = compile.matcher(key);
        String result = key;
        while (matcher.find()) {
            String tmp = matcher.group();
            result = matcher.replaceFirst("." + tmp.substring(1, tmp.length() - 1));
            matcher = compile.matcher(result);
        }
        return result.replaceAll("\\.\\.", ".").replaceAll("\\.$", "");
    }

}

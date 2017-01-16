package com.any.pub;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ExceptionResolves 统一异常处理类
 * Created by avaio on 2016/12/21.
 */
public class ExceptionResolves implements HandlerExceptionResolver {
    private static Logger logger = Logger.getLogger("AnExcption");

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        AnException ax;
        if (e instanceof AnException) {
            ax = (AnException) e;
        } else {
            ax = new AnException(e);
        }
        ax.handleException();
        if (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {// JSP格式返回
            return new ModelAndView("error.html");
        } else {// JSON格式返回
            try {
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(new ResponseEntity(false, ax.getMessage()), false));
                writer.flush();
                writer.close();
                response.getOutputStream().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
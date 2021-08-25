package com.api.bookstore.app.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class BookStoreLoggerInterceptor extends WebRequestHandlerInterceptorAdapter {


    public BookStoreLoggerInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("BookStoreLoggerInterceptor :: preHandle method execution- Begin");
        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);
        log.info("Request URL::" + request.getRequestURL().toString());
        log.info("Request Method::" + request.getMethod());


        log.info("BookStoreLoggerInterceptor :: preHandle method execution- End");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("BookStoreLoggerInterceptor :: postHandle method execution- Begin");

        log.info("Request URL::" + request.getRequestURL().toString());
        log.info("Request Method::" + request.getMethod());

        log.info("BookStoreLoggerInterceptor :: postHandle method execution- End");


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("BookStoreLoggerInterceptor :: afterCompletion method execution- Begin");




        long startTime = (Long) request.getAttribute("startTime");
        response.getHeaderNames().stream().peek(System.out::println);
        if (ex != null)
            ex.printStackTrace();

        log.info("Request URL::" + request.getRequestURL().toString()
                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));

        log.info("BookStoreLoggerInterceptor :: afterCompletion method execution- End");

    }



}

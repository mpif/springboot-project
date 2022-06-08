package com.codefans.springboot.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: codefans
 * @Date: 2022-05-25 15:55
 */

public class LimitingIntercepter implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(LimitingIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request:[{}] enter!", request.getRequestURL().toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("request:[{}] handling!", request.getRequestURL().toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("request:[{}] quit!", request.getRequestURL().toString());
    }
}

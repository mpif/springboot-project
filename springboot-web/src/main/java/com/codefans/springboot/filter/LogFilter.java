package com.codefans.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: codefans
 * @Date: 2022-07-06 19:03
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "logFilter")
public class LogFilter implements Filter {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURL = req.getRequestURL().toString();
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String pathInfo = req.getPathInfo();
        log.info("requestURL={}, requestURI={}, contextPath={}, servletPath={}, pathInfo={}", requestURL, requestURI, contextPath, servletPath, pathInfo);
        chain.doFilter(request, response);
    }
}

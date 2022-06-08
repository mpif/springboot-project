package com.codefans.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Author: codefans
 * @Date: 2022-06-01 15:43
 */

public class AbstractController {

    private Logger log = LoggerFactory.getLogger(AbstractController.class);

    protected void commonHystrixFallback(String method) {
        log.info("method:[" + method + "] fallback!");
    }

}

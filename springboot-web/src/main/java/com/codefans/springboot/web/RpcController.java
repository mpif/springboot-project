package com.codefans.springboot.web;

import com.codefans.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: codefans
 * @Date: 2022-07-31 12:19
 */
@RestController
public class RpcController {

    /**
     *
     */
    private Logger log = LoggerFactory.getLogger(RpcController.class);
    @Resource
    private UserService userService;

    @Resource
    private ServerProperties serverProperties;
    @RequestMapping("/rpcTest")
    public String rpcTest() {
        ServerProperties.Tomcat tomcatConfig = serverProperties.getTomcat();
        int maxThreads = tomcatConfig.getThreads().getMax();
        int minSpare = tomcatConfig.getThreads().getMinSpare();
        int acceptCount = tomcatConfig.getAcceptCount();
        int maxConnections = tomcatConfig.getMaxConnections();
        log.info("rpcTest(), maxThreads={}, minSpare={}, acceptCount={}, maxConnections={}", maxThreads, minSpare, acceptCount, maxConnections);
        return userService.queryUser();
    }

}

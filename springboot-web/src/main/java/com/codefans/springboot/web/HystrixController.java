package com.codefans.springboot.web;

import com.codefans.springboot.common.DateUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: codefans
 * @Date: 2022-06-01 14:57
 */
@RestController()
@RequestMapping("/hystrix")
public class HystrixController extends AbstractController {

    private static Logger log = LoggerFactory.getLogger(HystrixController.class);

    private volatile AtomicLong currentUser = new AtomicLong(0);

    @HystrixCommand(groupKey = "breakerCommandGroup", commandKey = "breakerCommandKey", fallbackMethod = "circuitBreakerFallback")
    @GetMapping("/circuitBreaker")
    public String circuitBreaker(@RequestParam(value = "name", defaultValue = "World") String name) {
        currentUser.getAndIncrement();
        log.info("current user count={}, name={}", currentUser.get(), name);
        currentUser.getAndDecrement();
        return String.format("Hello user[%s], currentCount[%s]!", name, currentUser.get());
    }

    /**
     * @param name
     * @return
     * @throws Exception
     */
    public void circuitBreakerFallback(String name) throws Exception {
        log.info("circuitBreakerFallback, name={}", name);
        super.commonHystrixFallback("com.codefans.springboot.web.HystrixController.circuitBreakerFallback");
    }

}

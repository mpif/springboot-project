package com.codefans.springboot.web;

import com.codefans.springboot.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: codefans
 * @Date: 2022-05-24 10:19
 */
@RestController()
@RequestMapping("/rateLimit")
public class RateLimitController {

    /**
     *
     */
    private static Logger log = LoggerFactory.getLogger(RateLimitController.class);

    private volatile long currentSecond = 0L;
    private volatile AtomicLong currentCount = new AtomicLong(0);

    @GetMapping("/slidingWindow")
    public String slidingWindow(@RequestParam(value = "name", defaultValue = "World") String name) {

        long currentSecond = DateUtils.currentSecond();
        if(currentSecond > this.currentSecond) {
            this.currentSecond = currentSecond;
            currentCount.set(0);
        } else {
            currentCount.getAndIncrement();
        }
        log.info("并发数currentCount=[{}]", currentCount.get());
        return String.format("Hello %s, current user:[%s]!", name, currentCount.get());
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        long currentSecond = DateUtils.currentSecond();
        if(currentSecond > this.currentSecond) {
            this.currentSecond = currentSecond;
            currentCount.set(0);
        } else {
            currentCount.getAndIncrement();
        }
        log.info("并发数currentCount=[{}]", currentCount.get());
        return String.format("Hello %s, current user:[%s]!", name, currentCount.get());
    }


}

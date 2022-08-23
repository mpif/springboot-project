package com.codefans;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: codefans
 * @Date: 2022-07-31 11:05
 */
@EnableDubbo
@SpringBootApplication
public class SpringbootProviderStartup {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootProviderStartup.class,args);
    }
}

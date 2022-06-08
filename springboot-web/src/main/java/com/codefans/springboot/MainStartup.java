package com.codefans.springboot; /**
 * @Author: codefans
 * @Date: 2022-05-23 23:18
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass=true)
//@EnableTransactionManagement
//@ComponentScan(basePackageClasses = {ApolloCommonConfig.class})
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.codefans"})
public class MainStartup {

    public static void main(String[] args) {
        SpringApplication.run(MainStartup.class, args);
    }

}

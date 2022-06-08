package com.codefans.springboot;

import com.codefans.springboot.intercepter.LimitingIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: codefans
 * @Date: 2022-05-25 16:06
 */
@Configuration
public class SpringBootConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LimitingIntercepter())
                .addPathPatterns("/rateLimit/slidingWindow")
                .excludePathPatterns("/rateLimit/hello");
    }
}

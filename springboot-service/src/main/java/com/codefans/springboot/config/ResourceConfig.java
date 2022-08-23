package com.codefans.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: codefans
 * @Date: 2022-07-04 19:01
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    private Logger log = LoggerFactory.getLogger(ResourceConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String pathPatterns = "/profile/**";
//        String resourceLocation = "file:" + ApplicationConfig.getUploadPath() + "/";
        String resourceLocation = "file:D:/Temp/eciticcfc/uploadPath/";
        log.info("resourceLocation:");
        log.info(resourceLocation);
        registry.addResourceHandler(pathPatterns).addResourceLocations(resourceLocation);
    }

//    该方法也可用于设置默认首页, 但是必须配置thymeleaf模板
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//    }



}

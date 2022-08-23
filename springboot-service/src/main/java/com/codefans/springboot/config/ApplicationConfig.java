package com.codefans.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: codefans
 * @Date: 2022-07-04 18:24
 */
@Configuration
public class ApplicationConfig {

    private static String uploadPath;

    @Value("${eciticcfc.uploadPath}")
    public void setUploadPath(String uploadPath) {
        ApplicationConfig.uploadPath = uploadPath;
    }

    public static String getUploadPath() {
        return uploadPath;
    }
}

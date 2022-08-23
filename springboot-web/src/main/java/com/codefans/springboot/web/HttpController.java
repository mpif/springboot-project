package com.codefans.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: codefans
 * @Date: 2022-07-08 10:00
 */
@RestController
public class HttpController {

    /**
     * 通用上传请求
     */
    @GetMapping("/http/header")
    @ResponseBody
    public String uploadFile(HttpServletRequest request) throws Exception {
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("X-Real-IP=").append(request.getHeader("X-Real-IP")).append(",")
              .append("X-forwarded-For=").append(request.getHeader("X-forwarded-For")).append(",")
              .append("x-forwarded-for=").append(request.getHeader("x-forwarded-for")).append(",")
              .append("remoteAddress=").append(request.getRemoteAddr()).append(",")
              .append("Proxy-Client-IP=").append(request.getHeader("Proxy-Client-IP")).append(",")
              .append("WL-Proxy-Client-IP=").append(request.getHeader("WL-Proxy-Client-IP")).append(",");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}

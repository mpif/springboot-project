package com.codefans.springboot.web;

import com.codefans.springboot.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: codefans
 * @Date: 2022-07-01 18:06
 */
@RestController
public class FileUploadController {

    /**
     * 上传服务
     */
    @Resource
    private UploadService uploadService;

    /**
     * 通用上传请求
     */
    @PostMapping("/admin/common/upload")
    public void uploadFile(MultipartFile file) throws Exception {
        try {
            String fileName = uploadService.upload(file);
            System.out.println("fileName=" + fileName);
//            ajax.put("fileName", fileName);
//            ajax.put("url", serverConfig.concatUrl(fileName));
//            return ajax;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

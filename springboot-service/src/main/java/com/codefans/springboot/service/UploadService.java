package com.codefans.springboot.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: codefans
 * @Date: 2022-07-04 18:28
 */

public interface UploadService {

    /**
     * 批量上传
     * @param multiFile
     * @return
     */
    String upload(MultipartFile multiFile);

}

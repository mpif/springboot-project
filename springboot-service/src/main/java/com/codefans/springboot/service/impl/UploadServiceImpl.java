package com.codefans.springboot.service.impl;

import com.codefans.springboot.config.ApplicationConfig;
import com.codefans.springboot.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: codefans
 * @Date: 2022-07-04 18:28
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public String upload(MultipartFile multiFile) {
        String uploadPath = ApplicationConfig.getUploadPath();
        String fileName = multiFile.getOriginalFilename();
        String newFilePath = uploadPath + File.separator + fileName;
        try {
            File newFile = new File(newFilePath);
            multiFile.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newFilePath;
    }

}

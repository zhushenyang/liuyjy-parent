package com.example.apiuser.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * 介绍：设置文件传输大小
 * Author:liuyjy
 * DATE: 2018/9/1
 * Copy:北京汉艺国际  @ 2018
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 置文件大小限制 ,超出此大小页面会抛出异常信息
        factory.setMaxFileSize("200MB"); //KB,MB
        // 设置总上传数据总大小
        factory.setMaxRequestSize("200MB");
        // 设置文件临时文件夹路径
        String location = System.getProperty("user.dir") + "/data/tmp";
        System.out.println("===========================" + location);
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        // 如果文件大于这个值，将以文件的形式存储，如果小于这个值文件将存储在内存中，默认为0
        return factory.createMultipartConfig();
    }
}

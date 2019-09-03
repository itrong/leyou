package com.leyou.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 乐优商城图片上传服务器启动类
 *
 * @author lujianrong
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LyUploadService {

    public static void main(String[] args) {
        SpringApplication.run(LyUploadService.class, args);
    }
}

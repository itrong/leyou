package com.leyou.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片文件上传
 *
 * @author lujianrong
 */
public interface UploadService {

    String upload(MultipartFile file);
}

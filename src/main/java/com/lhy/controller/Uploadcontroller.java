package com.lhy.controller;


import com.lhy.utils.AliyunOSSOperator;
import com.lhy.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class Uploadcontroller {
    // 上传文件到本地磁盘目录
//    private static final String UPLOAD_DIR = "D:/images/";
//    /**
//     * 上传文件
//     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("上传文件 {} {} {}", name, age, file);
//        // 获取文件名，用于保存
//        String originalFilename = file.getOriginalFilename();
//        // 获取文件扩展名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        // 生成新的文件名，避免文件名冲突
//        String newfilename = UUID.randomUUID().toString() + extension;
//        // 保存文件
//        file.transferTo(new File(UPLOAD_DIR + newfilename));
//        if (!new File(UPLOAD_DIR).exists()) {
//            new File(UPLOAD_DIR).mkdirs();
//        }
//        return Result.success();

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    // 上传文件到阿里云OSS
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件 {}", file);
        // 调用阿里云OSS上传文件的方法
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("上传文件成功 {}", url);
        return Result.success(url);
    }
}


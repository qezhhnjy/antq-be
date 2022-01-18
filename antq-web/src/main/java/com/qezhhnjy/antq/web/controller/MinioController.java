package com.qezhhnjy.antq.web.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.web.util.MinioUtil;
import io.minio.messages.Bucket;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/1/18-10:38
 */
@Slf4j
@RestController
@RequestMapping("/minio")
public class MinioController {

    @Resource
    private MinioUtil minioUtil;

    @ApiOperation(value = "查看存储bucket是否存在")
    @GetMapping("/bucketExists")
    public BaseResult<Boolean> bucketExists(String bucketName) throws Exception {
        return BaseResult.success(minioUtil.bucketExists(bucketName));
    }

    @ApiOperation(value = "创建存储bucket")
    @GetMapping("/makeBucket")
    public BaseResult<Void> makeBucket(String bucketName) throws Exception {
        minioUtil.makeBucket(bucketName);
        return BaseResult.success();
    }

    @ApiOperation(value = "删除存储bucket")
    @GetMapping("/removeBucket")
    public BaseResult<Void> removeBucket(String bucketName) throws Exception {
        minioUtil.removeBucket(bucketName);
        return BaseResult.success();
    }

    @ApiOperation(value = "获取全部bucket")
    @GetMapping("/getAllBuckets")
    public BaseResult<List<Bucket>> getAllBuckets() throws Exception {
        return BaseResult.success(minioUtil.getAllBuckets());
    }


    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public BaseResult<String> upload(MultipartFile file) throws Exception {
        String fileName = minioUtil.upload(file);
        return BaseResult.success(fileName);
    }

    @ApiOperation(value = "图片预览")
    @GetMapping("/preview")
    public BaseResult<String> preview(String fileName) throws Exception {
        return BaseResult.success(minioUtil.preview(fileName));
    }

    @ApiOperation(value = "文件下载")
    @GetMapping("/download")
    public BaseResult<Void> download(String fileName, HttpServletResponse res) {
        minioUtil.download(fileName, res);
        return BaseResult.success();
    }

    @ApiOperation(value = "文件删除")
    @GetMapping("/remove")
    public BaseResult<Void> remove(String fileName) throws Exception {
        minioUtil.remove(fileName);
        return BaseResult.success();
    }

}

package com.qezhhnjy.antq.web.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.qezhhnjy.antq.entity.pic.Album;
import com.qezhhnjy.antq.web.config.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件服务器工具类
 */
@Component
@Slf4j
public class MinioUtil {

    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioConfig minioConfig;

    /**
     * 查看存储bucket是否存在
     *
     * @return boolean
     */
    public Boolean bucketExists(String bucketName) throws Exception {
        boolean found;
        found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        return found;
    }

    /**
     * 创建存储bucket
     */
    public void makeBucket(String bucketName) throws Exception {
        minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
    }

    /**
     * 删除存储bucket
     */
    public void removeBucket(String bucketName) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder()
                .bucket(bucketName)
                .build());
    }

    /**
     * 获取全部bucket
     */
    public List<Bucket> getAllBuckets() throws Exception {
        return minioClient.listBuckets();
    }

    /**
     * 文件上传
     *
     * @param file 文件
     * @return Boolean
     */
    public String upload(MultipartFile file) throws Exception {
        // 按月存储
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String fileName = month + StrUtil.SLASH + file.getOriginalFilename();
        PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(minioConfig.getBucket()).object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType()).build();
        minioClient.putObject(objectArgs);
        return minioConfig.getPrefix() + minioConfig.getBucket() + StrUtil.SLASH + fileName;
    }

    public String upload(String dir, String name, Album album) throws Exception {
        String station = album.getStation();
        String title = album.getTitle();
        String fileName = station + StrUtil.SLASH + title + StrUtil.SLASH + name;
        File file = FileUtil.file(dir, name);
        minioClient.putObject(PutObjectArgs.builder().bucket(minioConfig.getBucket()).object(fileName)
                .stream(FileUtil.getInputStream(file), FileUtil.size(file), -1)
                .build());
        return minioConfig.getPrefix() + minioConfig.getBucket() + StrUtil.SLASH + fileName;
    }

    /**
     * 预览图片
     */
    public String preview(String fileName) throws Exception {
        // 查看文件地址
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder().bucket(minioConfig.getBucket()).object(fileName).method(Method.GET).build();
        return minioClient.getPresignedObjectUrl(build);
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     * @param res      response
     */
    public void download(String fileName, HttpServletResponse res) {
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(minioConfig.getBucket())
                .object(fileName).build();
        try (GetObjectResponse response = minioClient.getObject(objectArgs)) {
            byte[] buf = new byte[1024];
            int len;
            try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
                while ((len = response.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                os.flush();
                byte[] bytes = os.toByteArray();
                res.setCharacterEncoding("utf-8");
                //设置强制下载不打开
                //res.setContentType("application/force-download");
                res.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                try (ServletOutputStream stream = res.getOutputStream()) {
                    stream.write(bytes);
                    stream.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看文件对象
     *
     * @return 存储bucket内文件对象信息
     */
    public List<Item> listObjects() {
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(minioConfig.getBucket()).build());
        List<Item> items = new ArrayList<>();
        try {
            for (Result<Item> result : results) {
                items.add(result.get());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return items;
    }

    /**
     * 删除
     */
    public void remove(String fileName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(minioConfig.getBucket()).object(fileName).build());
    }

    /**
     * 批量删除文件对象（没测试）
     *
     * @param objects 对象名称集合
     */
    public Iterable<Result<DeleteError>> removeObjects(List<String> objects) {
        List<DeleteObject> dos = objects.stream().map(DeleteObject::new).collect(Collectors.toList());
        return minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(minioConfig.getBucket()).objects(dos).build());
    }

}
package com.dakkk.dkblog.admin.utils;

import com.dakkk.dkblog.admin.config.MinioProperties;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * ClassName: MinioUtil
 * Package: com.dakkk.dkblog.admin.utils
 *
 * @Create 2024/5/7 16:50
 * @Author dakkk
 * Description:
 */
@Component
@Slf4j
public class MinioUtil {
    @Autowired
    private MinioProperties minioProperties;
    @Autowired
    private MinioClient minioClient;

    /**
     * 上传文件方法
     */
    public String uploadFile(MultipartFile file) throws Exception {
        // 判断文件是否为空
        if (null == file && file.getSize() == 0) {
            log.error("==> 上传文件异常：文件大小为空 。。。");
            throw new RuntimeException("文件大小不能为空");
        }

        // 文件的原始名称
        String originalFilename = file.getOriginalFilename();

        // 生成存储对象的名称（使用hash前8位，在大文件上可能会碰撞）
        String hash = caculateFileHash(file.getInputStream()).substring(0, 8);
        // 获取当前日期的字符串
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 获取文件的后缀，如 .jpg 等
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 拼接成即将要存储的文件名
        String objectName = String.format("%s-%s%s", dateStr, hash, suffix);
        // 判断是否存在文件了，存在直接返回即可
        Iterable<Result<Item>> iterable = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(minioProperties.getBucketName())
                .build());
        String existedObjectName = StreamSupport.stream(iterable.spliterator(), false).map(itemResult -> {
                    try {
                        return itemResult.get().objectName();
                    } catch (ErrorResponseException e) {
                        throw new RuntimeException(e);
                    } catch (InsufficientDataException e) {
                        throw new RuntimeException(e);
                    } catch (InternalException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidKeyException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidResponseException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (ServerException e) {
                        throw new RuntimeException(e);
                    } catch (XmlParserException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(item -> StringUtils.equals(item,objectName))
                .findFirst().orElse(null);
        if (StringUtils.isNotBlank(existedObjectName)){
            log.info("==> 今日已上传过该文件至 Minio ，文件名: {}", objectName);
            return String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(),
                    existedObjectName);
        }

        log.info("==> 开始上传文件至 Minio, ObjectName: {}", objectName);
        // 文件的 Content-Type
        String contentType = file.getContentType();

        // 上传文件至 Minio
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(objectName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(contentType)
                .build());

        // 返回文件的访问链接
        String url = String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(),
                objectName);
        log.info("==> 上传文件至 Minio 成功，访问路径: {}", url);
        return url;
    }

    /**
     * 计算文件的Hash值，使用 SHA-256算法
     */
    private String caculateFileHash(InputStream inputStream) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // StringBuilder content = new StringBuilder();
        // try (Scanner scan = new Scanner(inputStream)) {
        //     while (scan.hasNextLine()) {
        //         content.append(scan.nextLine());
        //     }
        // }
        // byte[] hash = digest.digest(content.toString().getBytes());
        // return Base64.getEncoder().encodeToString(hash);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        StringBuilder sb = new StringBuilder();
        byte[] inputBytes = digest.digest();
        for (byte b : inputBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

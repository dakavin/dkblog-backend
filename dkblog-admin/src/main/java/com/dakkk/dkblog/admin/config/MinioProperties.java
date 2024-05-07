package com.dakkk.dkblog.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: MinioProperties
 * Package: com.dakkk.dkblog.admin.config
 *
 * @Create 2024/5/7 16:46
 * @Author dakkk
 * Description:
 */
@ConfigurationProperties(prefix = "minio")
@Component
@Data
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}

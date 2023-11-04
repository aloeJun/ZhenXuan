package cn.islu.zx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author aloeJun
 * @date 2023/10/24 10:22
 * @description: TODO
 */
@Data
@ConfigurationProperties(prefix = "zx.minio")
public class MinioProperties {
    private String endpointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;
}

package cn.islu.zx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author aloeJun
 * @date 2023/10/16 10:11
 * @description: TODO
 */
@Data
@ConfigurationProperties(prefix = "zx.auth")
public class UserAuthProperties {
    private List<String> noAuthUrls;

}
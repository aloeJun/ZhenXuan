package cn.islu.zx.manager;

import cn.islu.zx.manager.properties.MinioProperties;
import cn.islu.zx.manager.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author aloeJun
 * @date 2023/10/14 18:01
 * @description: Manager启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.islu.zx"})
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
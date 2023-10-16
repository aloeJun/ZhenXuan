package cn.islu.zx.manager.config;


import cn.islu.zx.manager.interceptor.LoginAuthInterceptor;
import cn.islu.zx.manager.properties.UserAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author aloeJun
 * @date 2023/10/15 12:54
 * @description: 全局跨域请求配置
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    private UserAuthProperties userAuthProperties;

    /**
     * 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
//                .excludePathPatterns("/admin/system/index/login", "/admin/system/index/generateValidateCode")
                .excludePathPatterns(userAuthProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }

    /**
     * 跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }

}
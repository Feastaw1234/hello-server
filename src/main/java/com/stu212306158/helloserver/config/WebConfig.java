package com.stu212306158.helloserver.config;

import com.stu212306158.helloserver.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 配置类
 * @Configuration：标识为配置类，项目启动自动加载
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/api/**")       // 拦截 /api 下所有接口
                .excludePathPatterns(             // 放行指定接口
                        "/api/users/login",
                        "/api/users"
                );
    }
}
package com.example.apispringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/8 13:27
 * @Description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/test为前缀的 url路径
        registry.addInterceptor(new Interceptor()).addPathPatterns("/test/**");
    }
}

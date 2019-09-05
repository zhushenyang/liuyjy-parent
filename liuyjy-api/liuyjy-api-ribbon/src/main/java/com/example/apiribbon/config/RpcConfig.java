package com.example.apiribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/25 13:30
 * @Description: 远程调用配置
 */
@Configuration
public class RpcConfig {
    /**
     * 在工程的启动类中,通过@EnableDiscoveryClient向服务中心注册；
     * 并且向程序的ioc注入一个bean: restTemplate;
     * 并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能。
     *
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

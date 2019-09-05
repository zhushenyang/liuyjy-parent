package com.example.cloudgateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/8 13:34
 * @Description: 限流
 */
@Configuration
public class RateLimiterGatewayFilterFactory {
    /**
     * 根据IP限流
     * @return
     */
    @Bean(name = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     *这种方式限流，请求路径中必须携带userId参数
     */
   /* @Bean(name = "userKeyResolver")
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }*/

    /**
     * 获取请求地址的uri作为限流key
     * @return
     */
   /* @Bean(name = "apiKeyResolver")
    KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }*/
}

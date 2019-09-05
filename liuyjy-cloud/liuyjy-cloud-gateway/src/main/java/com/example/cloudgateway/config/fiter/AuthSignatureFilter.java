package com.example.cloudgateway.config.fiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/12 13:31
 * @Description:
 */
@Slf4j
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("请求体=========="+exchange.getRequest());
        // 记录下请求内容
        log.info("<<<<<<<<<<<<<开始处理：《" + new Date() + "》<<<<<<<<<<<");
        log.info("URL : " + exchange.getRequest().getPath());
        log.info("HTTP_METHOD : " + exchange.getRequest().getMethodValue());
        log.info("QueryParams : " + exchange.getRequest().getQueryParams());
       /* String token = exchange.getRequest().getQueryParams().getFirst("authToken");
        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }*/
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}

package com.example.demo.redLock.lockconfig;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 15:31
 * @Description: Redisson连接
 */
@Component
public class RedissonConnector {
    RedissonClient redisson;

    @PostConstruct
    public void init() {
        redisson = Redisson.create();
    }

    public RedissonClient getClient() {
        return redisson;
    }
}

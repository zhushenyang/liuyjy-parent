package com.example.apiredisson.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 17:34
 * @Description:
 */
@Slf4j
@RestController
public class RedissonBucketController {
    @Resource
    private RedissonClient redissonClient;

    @GetMapping("{key}")
    public String get(@PathVariable String key) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        String value = bucket.get();
        long keyCount = redissonClient.getKeys().count();
        log.info("keyCount=" + keyCount + "*****--value=" + value);
        return "keyCount=" + keyCount + "*****--value=" + value;
    }

    @GetMapping("{key}/{value}")
    public String put(@PathVariable String key, @PathVariable String value) {
        redissonClient.getBucket(key).set(value, 5, TimeUnit.MINUTES);
        long keyCount = redissonClient.getKeys().count();
        log.info("keyCount=" + keyCount + "*****--value=" + value);
        return "keyCount=" + keyCount;
    }

}

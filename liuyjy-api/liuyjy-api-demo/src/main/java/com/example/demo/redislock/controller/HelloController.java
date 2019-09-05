package com.example.demo.redislock.controller;

import com.example.demo.redislock.entity.Lock;
import com.example.demo.redislock.lockconfig.DistributedLockHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 14:50
 * @Description:
 */
@RestController
public class HelloController {
    @Autowired
    private DistributedLockHandler distributedLockHandler;

    @RequestMapping("redisindex")
    public String index() {
        Lock lock = new Lock("lynn", "min");
        if (distributedLockHandler.tryLock(lock)) {
            try {
                //为了演示锁的效果，这里睡眠5000毫秒
                System.out.println("执行方法");
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            distributedLockHandler.releaseLock(lock);
        } else {
            System.out.println("当前处理被锁住！");
        }
        return "hello world!";
    }
}

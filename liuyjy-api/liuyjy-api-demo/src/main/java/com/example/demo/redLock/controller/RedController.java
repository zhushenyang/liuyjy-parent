package com.example.demo.redLock.controller;

import com.example.demo.redLock.service.AquiredLockWorker;
import com.example.demo.redLock.service.DistributedLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 15:38
 * @Description:
 */
@RestController
public class RedController {
    @Autowired
    private DistributedLocker distributedLocker;

    @RequestMapping("index")
    public String index() throws Exception {
        distributedLocker.lock("test", new AquiredLockWorker<Object>() {
            @Override
            public Object invokeAfterLockAquire() {
                try {
                    System.out.println("执行方法！");
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        });
        return "hello world!";
    }
}

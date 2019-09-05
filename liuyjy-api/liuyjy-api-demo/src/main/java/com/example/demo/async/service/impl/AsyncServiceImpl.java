package com.example.demo.async.service.impl;

import com.example.demo.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/26 16:39
 * @Description:
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void executeAsync(int i) {
        log.info("==============第" + i + "次========进来==========");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("==============第" + i + "次========执行完了==========");
    }

    @Override
    @Async
    public Future<String> executeAsync2(int i) {
        log.info("==============第" + i + "次========进来==========");
        Future<String> future;
        try {
            Thread.sleep(10000 * 1);
            future = new AsyncResult<String>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error" + i);
        }
        log.info("==============第" + i + "次========执行完了==========");
        return future;
    }
}

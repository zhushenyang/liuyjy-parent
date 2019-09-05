package com.example.demo.async.service;

import java.util.concurrent.Future;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/26 16:38
 * @Description:
 */
public interface AsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync(int i);

    /**
     * 又返回
     *
     * @param i
     * @return
     */
    Future<String> executeAsync2(int i);
}

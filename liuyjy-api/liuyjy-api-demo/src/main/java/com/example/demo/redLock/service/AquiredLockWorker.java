package com.example.demo.redLock.service;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 15:26
 * @Description: 获取锁后的处理逻辑
 */
public interface AquiredLockWorker<T> {

    T invokeAfterLockAquire() throws Exception;
}

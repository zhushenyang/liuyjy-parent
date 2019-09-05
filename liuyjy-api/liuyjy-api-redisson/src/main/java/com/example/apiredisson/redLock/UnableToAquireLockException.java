package com.example.apiredisson.redLock;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 15:29
 * @Description: 锁异常类
 */
public class UnableToAquireLockException extends RuntimeException {
    public UnableToAquireLockException() {
    }

    public UnableToAquireLockException(String message) {
        super(message);
    }

    public UnableToAquireLockException(String message, Throwable cause) {
        super(message, cause);
    }

}

package com.example.apiredisson.service;

import com.example.apiredisson.config.LockAction;
import com.example.apiredisson.entity.LockType;
import com.example.apiredisson.entity.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RedissonLockService {
    private final Logger logger = LoggerFactory.getLogger(RedissonLockService.class);

    @LockAction("'updateKey'")
    public void update(String key) {
        try {
            Thread.sleep(5000);
            logger.info("开始处理了");
        } catch (InterruptedException e) {
            logger.error("exp", e);
        }
        logger.info("处理完成");
    }

    @LockAction("#userVO.id")
    public void spel(UserVO userVO) {
        try {
            Thread.sleep(5000);
            logger.info("开始处理了");
        } catch (InterruptedException e) {
            logger.error("exp", e);
        }
        logger.info("处理完成");
    }

    @LockAction(value = "#userVO.id", lockType = LockType.WRITE_LOCK, waitTime = 30000)
    public void update(UserVO userVO) {
        logger.info("write user : {}", userVO.getId());
        try {
            Thread.sleep(500);
            logger.info("开始处理了");
        } catch (InterruptedException e) {
            logger.error("exp", e);
        }
        logger.info("处理完成");
    }

    @LockAction(value = "#userVO.id", lockType = LockType.READ_LOCK, waitTime = 30000)
    public UserVO read(UserVO userVO) {
        logger.info("read user : {}", userVO.getId());
        try {
            Thread.sleep(10000);
            logger.info("开始处理了");
        } catch (InterruptedException e) {
            logger.error("exp", e);
        }
        logger.info("处理完成");
        return userVO;
    }
}

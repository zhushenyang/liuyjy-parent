package com.example.apiredisson.controller;

import com.example.apiredisson.entity.UserVO;
import com.example.apiredisson.service.RedissonLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/8 18:53
 * @Description:
 */
@RestController
public class RedissonLock {
    @Autowired
    private RedissonLockService redissonLockService;

    /**
     * @return
     */
    @GetMapping("read")
    public String read(){
        UserVO userVO=new UserVO();
        userVO.setId("123");
        redissonLockService.read(userVO);
        return "hello world!";
    }

    @GetMapping("spel")
    public String spel(){
        UserVO userVO=new UserVO();
        userVO.setId("123");
        redissonLockService.spel(userVO);
        return "hello world!";
    }

    @GetMapping("update2")
    public String update2() throws Exception {
        UserVO userVO=new UserVO();
        userVO.setId("123");
        redissonLockService.update(userVO);
        return "hello world!";
    }

    @GetMapping("update")
    public String update(){
        redissonLockService.update("1234");
        return "hello world!";
    }
}

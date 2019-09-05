package com.example.controller;

import com.example.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: liuyjy
 * @Date: 2018/12/24 21:08
 * @Description:  切换库
 */
@Slf4j
@RestController
public class DataInpuController {

    @Autowired
    private TestService testService;

    @RequestMapping("test")
    public String test1() {
        log.info("=========DB_MASTER========="+testService.getTest1());
        log.info("=========DB_SLAVE1========="+testService.getTest2());
        log.info("=========DB_MASTER========="+testService.getTest1());
        log.info("=========DB_SLAVE1========="+testService.getTest2());
        log.info("=========DB_MASTER========="+testService.getTest1());
        log.info("=========DB_SLAVE1========="+testService.getTest2());


        return "s";
    }
}

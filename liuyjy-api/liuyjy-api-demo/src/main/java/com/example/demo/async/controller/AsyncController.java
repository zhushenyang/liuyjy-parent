package com.example.demo.async.controller;

import com.example.demo.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/26 16:40
 * @Description:
 */
@Slf4j
@RestController
@EnableSwagger2
public class AsyncController {
    @Autowired
    private AsyncService asyncService;
    private volatile int count = 0;

    @GetMapping("/async1")
    public String submit() {
        count++;
        int i = count;
        log.info("start submit============第" + i + "==次，请求进来");

        //调用service层的任务
        asyncService.executeAsync(i);

        log.info("end submit============第" + i + "==次，请求返回");

        return "success";
    }

    @GetMapping("/async2")
    public String submit2() throws ExecutionException, InterruptedException {
        count++;
        int i = count;
        log.info("start submit============第" + i + "==次，请求进来");

        //调用service层的任务
        Future<String> str = asyncService.executeAsync2(i);

        log.info("end submit======" + str.get().toString() + "======第" + i + "==次，请求返回");

        return "success";
    }
}

package com.example.apiribbon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@Slf4j
@SpringBootApplication
//@EnableHystrix注解开启断路器：
@EnableHystrix
public class LiuyjyApiRibbonApplication {

    public static void main(String[] args) {
        log.info("http://localhost:8081/ribbon/hi?name=123&token=1232");
        SpringApplication.run(LiuyjyApiRibbonApplication.class, args);
    }

}

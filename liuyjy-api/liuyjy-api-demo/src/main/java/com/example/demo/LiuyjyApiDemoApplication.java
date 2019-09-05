package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LiuyjyApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuyjyApiDemoApplication.class, args);
        log.info("http://localhost:6325/swagger-ui.html");
    }

}

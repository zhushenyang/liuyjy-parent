package com.example.apifeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LiuyjyApiFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuyjyApiFeignApplication.class, args);
    }

}

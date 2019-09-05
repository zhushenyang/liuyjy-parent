package com.example.apiuser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class LiuyjyApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuyjyApiUserApplication.class, args);
        log.info("http://localhost:8081/hart/market/sp/getAdminList");
        log.info("http://localhost:8081/hart/market/sp/kafka/producer");
    }

}

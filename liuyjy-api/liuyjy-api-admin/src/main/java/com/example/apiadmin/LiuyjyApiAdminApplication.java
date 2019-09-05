package com.example.apiadmin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class LiuyjyApiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuyjyApiAdminApplication.class, args);
        log.info("http://localhost:8081/hart/market/admin/getAdminList");
    }

}

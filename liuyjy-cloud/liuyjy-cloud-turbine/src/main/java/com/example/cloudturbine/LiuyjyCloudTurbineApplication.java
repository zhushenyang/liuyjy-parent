package com.example.cloudturbine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
@Slf4j
@EnableTurbine
//开启HystrixDashboard
@EnableHystrixDashboard
@SpringBootApplication
public class LiuyjyCloudTurbineApplication {

    public static void main(String[] args) {

        SpringApplication.run(LiuyjyCloudTurbineApplication.class, args);
        log.info("http://localhost:8769/hystrix");
    }

}

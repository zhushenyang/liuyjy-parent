package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication
@EnableTransactionManagement
public class LiuyjyApiMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuyjyApiMybatisPlusApplication.class, args);
    }

}

package com.example.apies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.apies.*"})
@SpringBootApplication
public class LiuyjyApiEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuyjyApiEsApplication.class, args);
    }

}

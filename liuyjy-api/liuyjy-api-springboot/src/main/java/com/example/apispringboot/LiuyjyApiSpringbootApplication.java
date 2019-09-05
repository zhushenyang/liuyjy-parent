package com.example.apispringboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LiuyjyApiSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuyjyApiSpringbootApplication.class, args);

        //SpringApplicationBuilder builder = new SpringApplicationBuilder(LiuyjyApiSpringbootApplication.class);
        //修改Banner的模式为OFF
       // builder.bannerMode(Banner.Mode.OFF).run(args);
    }

}

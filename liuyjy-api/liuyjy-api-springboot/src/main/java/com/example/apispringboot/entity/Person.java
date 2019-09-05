package com.example.apispringboot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/8 11:00
 * @Description:
 */
@Component
@PropertySource("classpath:person.properties")
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;

    private String age;

    private String english_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }
}

package com.example.demo.redLock.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 14:48
 * @Description:
 */
@Data
public class Lock implements Serializable {

    private String name;
    private String value;

    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

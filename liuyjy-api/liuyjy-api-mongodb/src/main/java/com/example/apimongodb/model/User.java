package com.example.apimongodb.model;

import java.io.Serializable;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 10:05
 * @Description:
 */
public class User implements Serializable {
    private Integer id;
    private Integer age;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

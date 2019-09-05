package com.example.apifeign.service;

import org.springframework.stereotype.Component;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/3 13:26
 * @Description:
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}

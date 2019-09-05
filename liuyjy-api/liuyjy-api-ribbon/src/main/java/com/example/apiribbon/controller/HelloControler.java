package com.example.apiribbon.controller;

import com.example.apiribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/3 10:18
 * @Description:
 */
@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    /**
     * 在浏览器上多次访问http://localhost:8764/hi?name=forezp
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }
}

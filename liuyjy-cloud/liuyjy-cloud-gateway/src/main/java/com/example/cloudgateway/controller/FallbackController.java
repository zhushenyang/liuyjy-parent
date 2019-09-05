package com.example.cloudgateway.controller;

import com.example.liuyjyentity.response.ResponseParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/8 13:52
 * @Description: 熔断
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public ResponseParam fallback() {
        ResponseParam response = new ResponseParam();
        response.setCode("100");
        response.setMessage("服务暂时不可用");
        return response;
    }
}

package com.example.apifeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/3 11:33
 * @Description:
 */
@FeignClient(value = "service-hi", fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
    /**
     * 定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务。
     * 比如在代码中调用了service-hi服务的“/hi”接口
     */

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}

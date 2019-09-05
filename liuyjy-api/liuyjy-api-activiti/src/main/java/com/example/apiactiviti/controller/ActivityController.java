package com.example.apiactiviti.controller;

import com.example.apiactiviti.service.ActivityConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/13 14:18
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class ActivityController {

    @Autowired
    private ActivityConsumerService activityConsumerService;
    /**
     * 流程demo
     *
     * @return
     */
    @RequestMapping(value = "/activitiDemo", method = RequestMethod.GET)
    public String startActivityDemo() {
        activityConsumerService.startActivityDemo();
        return "";
    }
}

package com.example.apiuser.controller;

import com.example.apiuser.service.AdminService;
import com.example.liuyjyentity.response.ResponseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/11 15:58
 * @Description:
 */
@RestController
public class SpController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/getAdminList")
    public ResponseParam getAdminList() {

        return adminService.getAdminList();
    }
}

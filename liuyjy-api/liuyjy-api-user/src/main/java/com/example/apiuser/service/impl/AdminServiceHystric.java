package com.example.apiuser.service.impl;


import com.example.apiuser.service.AdminService;
import com.example.liuyjyentity.response.HartReturnResult;
import com.example.liuyjyentity.response.ResponseParam;
import org.springframework.stereotype.Component;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/11 15:54
 * @Description:
 */
@Component
public class AdminServiceHystric implements AdminService {
    @Override
    public ResponseParam getAdminList() {
        return HartReturnResult.ok(null, "服务暂停");
    }
}

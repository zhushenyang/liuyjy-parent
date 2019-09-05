package com.example.apiuser.service;


import com.example.apiuser.service.impl.AdminServiceHystric;
import com.example.liuyjyentity.response.ResponseParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/11 15:52
 * @Description:
 */
@FeignClient(value = "admin-web", fallback = AdminServiceHystric.class)
public interface AdminService {
    @GetMapping(value = "/hart/market/admin/getAdminList")
    ResponseParam getAdminList();
}


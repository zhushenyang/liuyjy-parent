package com.example.apiadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.liuyjyentity.admin.AdminInfo;

import java.util.Map;

/**
 * 介绍：管理员管理页面
 * Author:liuyjy
 * DATE: 2018/8/31
 * Copy:北京汉艺国际  @ 2018
 */
public interface AdminService extends IService<AdminInfo> {
    /**
     * 管理员登陆
     *
     * @param map
     * @return
     */
    AdminInfo login(Map<String, Object> map);

    IPage<AdminInfo> getAdminList(Page<AdminInfo> page);


    /**
     * 通过同肯获取管理员信息
     *
     * @param token
     * @return
     */
    AdminInfo getokenAdminInfo(String token);

    /**
     * 管理员登陆token变更
     *
     * @param map
     * @return
     */
    boolean addAdmintoken(Map<String, Object> map);

    /**
     * 管理员退出登陆
     *
     * @param token
     * @return
     */
    boolean exitAdmin(String token);

    /**
     * 修改用户信息
     *
     * @param admin
     * @return
     */
    boolean updateAdminInfo(AdminInfo admin);

    /**
     * 用户名是否存在
     *
     * @param adminNo
     * @return
     */
    boolean adminNoIsExist(String adminNo);

}

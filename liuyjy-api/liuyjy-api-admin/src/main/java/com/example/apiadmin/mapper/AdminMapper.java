package com.example.apiadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.liuyjyentity.admin.AdminInfo;

import java.util.List;
import java.util.Map;


/**
 * 介绍：管理员管理
 * Author:liuyjy
 * DATE: 2018/8/31
 * Copy:北京汉艺国际  @ 2018
 */
public interface AdminMapper extends BaseMapper<AdminInfo> {

    /**
     * 管理员登陆
     *
     * @param map
     * @return
     */
    AdminInfo login(Map<String, Object> map);

    List<AdminInfo> getAdminList(Page<AdminInfo> page);

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
    int addAdmintoken(Map<String, Object> map);

    /**
     * 管理员退出登陆
     *
     * @param adminId
     * @return
     */
    int exitAdmin(String adminId);

    /**
     * 修改用户信息
     *
     * @param admin
     * @return
     */
    int updateAdminInfo(AdminInfo admin);

    /**
     * 用户名是否存在
     *
     * @param adminNo
     * @return
     */
    int adminNoIsExist(String adminNo);

}

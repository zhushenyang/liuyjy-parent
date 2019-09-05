package com.example.apiadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.apiadmin.mapper.AdminMapper;
import com.example.apiadmin.service.AdminService;
import com.example.liuyjycommon.util.UUIDGenerator;
import com.example.liuyjyentity.admin.AdminInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 介绍：
 * Author:liuyjy
 * DATE: 2018/8/31
 * Copy:北京汉艺国际  @ 2018
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminInfo> implements AdminService {

    @Override
    public AdminInfo login(Map<String, Object> map) {
        AdminInfo info = baseMapper.login(map);
        if (info != null) {
            String token = UUIDGenerator.getUUID();
            info.setToken(token);
            map.put("adminId", info.getAdminId());
            map.put("token", token);
            int i = baseMapper.addAdmintoken(map);
            if (i > 0) {

                return info;
            }
        }
        return null;
    }


    @Override
    public IPage<AdminInfo> getAdminList(Page<AdminInfo> page) {
        return page.setRecords(baseMapper.getAdminList(page));
    }

    @Override
    public AdminInfo getokenAdminInfo(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return baseMapper.getokenAdminInfo(token);
    }

    @Override
    public boolean addAdmintoken(Map<String, Object> map) {
        int i = baseMapper.addAdmintoken(map);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean exitAdmin(String token) {
        int i = baseMapper.exitAdmin(token);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdminInfo(AdminInfo admin) {
        int i = baseMapper.updateAdminInfo(admin);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminNoIsExist(String adminNo) {
        int i = baseMapper.adminNoIsExist(adminNo);
        if (i > 0) {
            return true;
        }
        return false;
    }
}

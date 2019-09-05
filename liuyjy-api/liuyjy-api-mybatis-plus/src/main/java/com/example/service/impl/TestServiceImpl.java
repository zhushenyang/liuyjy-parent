package com.example.service.impl;

import com.example.annotation.DataSource;
import com.example.enums.DataSourceEnum;
import com.example.liuyjycommon.util.GsonUtil;
import com.example.mapper.NoticeManageMapper;
import com.example.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author liuyjy
 * @Date 2019/8/22
 * @Description: TODO
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl implements TestService {

    @Autowired
    private NoticeManageMapper noticeManageMapper;


    @DataSource(DataSourceEnum.DB_MASTER)
    @Override
    public int getTest1() {
        List<Object> list=noticeManageMapper.getNoticeManage();
        log.info("============数据========"+ list.toString());
        return list.size();
    }

    @DataSource(DataSourceEnum.DB_SLAVE1)
    @Override
    public int getTest2() {
        List<Object> list=noticeManageMapper.getActivityHotelCoupon();
        log.info("============数据========"+ list.toString());
        return list.size();
    }
}

package com.example.apiadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.apiadmin.service.AdminService;
import com.example.liuyjyentity.admin.AdminInfo;
import com.example.liuyjyentity.constant.HartConstant;
import com.example.liuyjyentity.kafka.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * kafka消费者控制层
 *
 * @author yongzhen
 * @date 2019/4/10-15:47
 */
@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("kafka")
public class CustomerController {

    @Autowired
    private AdminService adminService;

    @KafkaListener(topics = KafkaConstant.TOPIC_NAME)
    public void kafkaCustomer(String receiveData) {
        // OrderInfo orderInfo = JSONObject.parseObject(receiveData, OrderInfo.class);
        log.info("收到消息: =====" + receiveData);
        int current = HartConstant.CUTTEBT;
        int size = HartConstant.SIZE;
        Page<AdminInfo> page = new Page<>(current, size);
        IPage<AdminInfo> list = adminService.getAdminList(page);

        log.info("处理成功");
        // System.err.println("我接收到消息:" + orderInfo.getAddress());
    }
}











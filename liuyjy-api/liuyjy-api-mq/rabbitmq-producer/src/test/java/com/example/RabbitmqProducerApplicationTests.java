package com.example;

import com.example.liuyjyentity.mq.Order;
import com.example.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProducerApplicationTests {
    @Autowired
    private OrderService orderService;
    @Test
    public void contextLoads() throws Exception {
        Order order = new Order();
        order.setId(2018092101);
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderService.createOrder(order);
    }

}

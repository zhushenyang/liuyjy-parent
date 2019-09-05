package com.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @Author liuyjy
 * @Date 2019/8/30 14:07
 * @Description: TODO
 **/
@RestController
public class KafkaController {
    @Autowired
    private KafkaProducer<User> kafkaSender;

    @GetMapping("kafka")
    public void kafkaSend() throws InterruptedException {
        //模拟发消息
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(System.currentTimeMillis());
            user.setMsg(UUID.randomUUID().toString());
            user.setSendTime(new Date());

            kafkaSender.send(user);
            Thread.sleep(3000);
        }
    }

}

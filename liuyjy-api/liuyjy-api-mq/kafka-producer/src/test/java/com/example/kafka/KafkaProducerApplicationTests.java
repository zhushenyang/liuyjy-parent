package com.example.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerApplicationTests {

    @Autowired
    private KafkaProducer<User> kafkaSender;

    @Test
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

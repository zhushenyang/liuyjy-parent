package com.example.apiuser.controller;

import com.example.liuyjyentity.kafka.KafkaConstant;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * kafka生产者控制层
 *
 * @author yongzhen
 * @date 2019/4/10-15:45
 */
@RestController
@RequestMapping("kafka")
public class ProducerController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @RequestMapping("producer")
    public void kafkaProducer() {
        /*OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNum("HART201904111312");
        orderInfo.setOrderSum(588);
        orderInfo.setAddress("朝阳区中海广场");
        orderInfo.setReceive("周休");
        orderInfo.setOrderTime(new Date());
        String sendData = JSONObject.toJSONString(orderInfo);*/
        String sendData = "我是消息！";
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaConstant.TOPIC_NAME, "i", sendData);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.warn("Sending message to Kafka failed: topic {}, content: {}", KafkaConstant.TOPIC_NAME, sendData);
            }

            @Override
            public void onSuccess(SendResult<String, String> producerData) {
                ProducerRecord<String, String> producerRecord = producerData.getProducerRecord();
                logger.info("Sending message to Kafka finished: topic {}, content: {}", KafkaConstant.TOPIC_NAME, producerRecord.value());
            }
        });
    }
}
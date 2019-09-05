package com.example.demo.sorl.service.impl;

import com.example.demo.sorl.entity.User;
import com.example.demo.sorl.service.SolrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/27 10:08
 * @Description:
 */
@Service
@Slf4j
public class SolrServiceImpl implements SolrService {

    @Override
    public List<User> addUser() {
        List<User> list = new ArrayList<>();
        User user;
        for (int i = 0; i < 5; i++) {
            user = new User();
            user.setId(UUID.randomUUID().toString().replace("-", ""));
            user.setName("jack" + i);
            if (i % 2 == 0) {
                user.setSex("男");
            } else {
                user.setSex("女");
            }
            user.setAddress("兰州市安宁区666" + i);
            user.setHost(73040 + i);
            user.setText("这样就实现了只对zwpp一个字段传入查询条件，就实现了对多个字段查询的功能。 \n" + "这种方式的缺点，我感觉对于solr创建索引的速度有影响，但是查询肯定会快。占用的空间也会大点。" + i);
            list.add(user);
        }
        return list;
    }
}

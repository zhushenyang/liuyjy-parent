package com.example.apimongodb.controller;

import com.example.apimongodb.dao.userDao;
import com.example.apimongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 09:58
 * @Description:
 */
@RestController
public class MongoController {
    @Autowired
    private userDao mtdao;

    @GetMapping(value = "/test1")
    public void saveTest() throws Exception {
        User mgtest = new User();
        mgtest.setId(11);
        mgtest.setAge(33);
        mgtest.setName("ceshi");
        mtdao.saveTest(mgtest);
    }

    @GetMapping(value = "/test2")
    public User findTestByName() {
        User mgtest = mtdao.findTestByName("ceshi");
        System.out.println("mgtest is " + mgtest);
        return mgtest;
    }

    @GetMapping(value = "/test3")
    public void updateTest() {
        User mgtest = new User();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName("ceshi2");
        mtdao.updateTest(mgtest);
    }

    @GetMapping(value = "/test4")
    public void deleteTestById() {
        mtdao.deleteTestById(11);
    }

}

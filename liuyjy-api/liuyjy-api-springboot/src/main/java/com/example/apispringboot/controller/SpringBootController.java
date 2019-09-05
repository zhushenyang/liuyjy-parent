package com.example.apispringboot.controller;

import com.example.apispringboot.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/8 11:03
 * @Description:
 */
@Slf4j
@RestController
public class SpringBootController {

    @RequestMapping("login")
    public String login(String name, String pwd, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(name.equals("test")&&pwd.equals("test123")) {
            session.setAttribute("user",name);
            return "登录成功";
        } else {
            return "用户名或密码错误!";
        }
    }

    @GetMapping(value="/test")
    public Integer test(){
        return 111;
    }

    @Autowired
    private Person person;
    @RequestMapping(value = "/person",produces = "text/plain;charset=UTF-8")
    String index(){
        log.info("Hello Spring Boot!，the person's info :the name "+person.getName()+"   ,and the age is " +person.getAge()+"  the englishname is "+person.getEnglish_name());
        return "Hello Spring Boot!，the person's info :the name "+person.getName()+"   ,and the age is " +person.getAge()+"  the englishname is "+person.getEnglish_name();
    }

    @Value("1234")
    private String c;
    @RequestMapping(value = "/c",method= RequestMethod.GET)
    String Say(@RequestParam(value="id",required = false,defaultValue = "999") Integer myId){
        return "the id is:"+myId;
    }

    @RequestMapping(value = "/c/{id}",method=RequestMethod.GET)
    String Say2(@PathVariable("id") Integer myId){
        return "the id is:"+myId;
    }


    public static void main(String[] args) {

        System.out.println("originStr=="+reverse("abcd"));
        LocalDateTime localDateTime=LocalDateTime.now();
    }

    public static String reverse(String originStr){
        //originStr.charAt(0)-=32
        if(null ==originStr || originStr.length()<=1){
            return originStr;
        }
       // new StringBuilder().reverse();
        System.out.println(originStr.charAt(0));
        return reverse(originStr.substring(1))+originStr.charAt(0);
    }

}

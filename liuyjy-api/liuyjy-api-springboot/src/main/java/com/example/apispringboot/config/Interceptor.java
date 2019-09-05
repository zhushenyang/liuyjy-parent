package com.example.apispringboot.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/8 13:28
 * @Description:
 */
public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这里写各种判断逻辑
        // 如果没有………………，可以使用 reponse.send() 跳转页面。后面要跟return false,否则无法结束;

        // 为了测试，打印一句话
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL().toString());
        System.out.println("访问了test下url路径。");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

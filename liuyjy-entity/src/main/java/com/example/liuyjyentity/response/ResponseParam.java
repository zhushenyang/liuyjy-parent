package com.example.liuyjyentity.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/25 11:49
 * @Description: 请求返回参数
 */
@Data
@Slf4j
public class ResponseParam implements Serializable {
    /**
     * 返回编码
     */
    private  String code;
    /**
     * 返回提示信息
     */
    private  String message;
    /**
     * 返回数据
     */
    private  Object data;
}

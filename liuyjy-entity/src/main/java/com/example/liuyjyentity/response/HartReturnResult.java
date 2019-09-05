package com.example.liuyjyentity.response;

import lombok.extern.slf4j.Slf4j;

/**
 * 介绍：运营后台返回信息
 * Author:liuyjy
 * DATE: 2018/9/1
 * Copy:北京汉艺国际  @ 2018
 */
@Slf4j
public class HartReturnResult {
//“0：正常，  1001：系统错误，1002：内部错误，1003：登陆过期

    /**
     * 成功
     *
     * @param msg
     * @return
     */
    public static ResponseParam ok(Object data, String msg) {
        ResponseParam result = new ResponseParam();
        result.setData(data);
        result.setCode("0");
        result.setMessage(msg);
        return result;
    }


    /**
     * 系统错误
     *
     * @param msg
     * @return
     */
    public static ResponseParam systemError(String msg) {
        ResponseParam result = new ResponseParam();
        result.setCode("1001");
        result.setMessage(msg);
        return result;
    }

}

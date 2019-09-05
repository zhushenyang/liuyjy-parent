package com.example.liuyjyentity.constant;

/**
 * 介绍：常量定义
 * Author:liuyjy
 * DATE: 2018/8/29
 * Copy:北京汉艺国际  @ 2018
 */
public class HartConstant {

    // 版本号
    public static final String VERS = "1.0";

    // 课程
    public static final String KECHENG = "1";
    // 课程名称
    public static final String KECHENGNAME = "课程";

    // 课程图片
    public static final String KECHENGPICURL = "";

    //文章
    public static final String WENZHANG = "2";
    //初始化用户id
    public static final String USERID = "0";
    //无权限
    public static final int NOACCESS = 4;

    public static final String CONTENT_CHARSET = "UTF-8";

    public static final String CONTENT_CHARSETNAME = "utf8";
    //腾讯云secretId
    public static final String QCLOUDSECRETID = "AKIDzwpklksaZNzjZEnbO0cLI9xeDi1JgXjq";

    //腾讯云secretKey
    public static final String QCLOUDSECRETKEY = "FPXXN3hDZkv8VTQoAORL8x1LagaZMxaA";

    //腾讯云签名算法
    public static final String QCLOUDMETHOD = "HmacSHA1";
    //腾讯云视频分类
    public static final Integer QCLOUDCLASSID = 508987;


    //是否有效
    public static final int ISVALID = 1;
    //排序
    public static final int NO = 0;
    //搜索默认值
    public static final String CRITERIA = "";
    //默认第一页
    public static final int CUTTEBT = 1;
    //默认10条数据
    public static final int SIZE = 200;
    //默认性别:性别：1：男，2：女
    public static final int SEX = 1;
    //默认年龄
    public static final int AGE = 0;

    //默认点赞数
    public static final int LIKENUM = 0;
    //默认状态：在线状态， 0:下线， 1：上线，2：仅保存
    public static final int ONLINESTATUS = 2;

    //AppID(小程序ID)
    public static final String APPLETAPPID = "wxa1dc16c1be86f28f";

    //AppSecret(小程序密钥)
    public static final String APPLETSECRET = "10908711c0144ef291d7f322303e2efd";

    //微信支付商户号
    public static final String MCH_ID = "1516708831";

    //微信商铺支付API支付密钥
    public static final String APPLETKEY = "ea62189dac43021c432d07979e73188e";

    //退款证书url
    public static final String ZHENGSHUURL = "ea62189dac43021c432d07979e73188e";
    //支付回调地址
    public static final String NOTIFYURL="http://119.57.164.23:8686/hart/market/api/orders/applatCallBack";
    //public static final String NOTIFYURL = "https://scxcx.hartedu.com/hart/market/api/orders/applatCallBack";

    // 文件上传地址
    public static final String FILEPATH = "/public/hart_file/file/";
    //public static final String FILEPATH = "/home/public/hart_file/";

    // 文件服务器地址
    public static final String FILESERVICE = "http://10.0.192.55/file/";
    //public static final String FILESERVICE = "http://file.hartedu.com/";

}

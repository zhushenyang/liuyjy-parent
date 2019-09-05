package com.example.liuyjyentity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员信息表
 * </p>
 *
 * @author liuyjy
 * @since 2018-10-31
 */
@Data
public class AdminInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 内部用id
     */
    private String adminId;

    /**
     * 登录名
     */
    private String adminNo;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别：1：男，2：女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String picurl;

    /**
     * 用户登陆标记
     */
    private String token;

    /**
     * 登陆密码md5
     */
    private String pwd;

    /**
     * 最后登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;

    /**
     * 最后退出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastExitTime;

    /**
     * 用户类型：1:开发部,2:管理员,3:编辑,4:运营
     */
    private Integer adminType;

    /**
     * 用户状态:1：正常，2、冻结
     */
    private Integer adminStatus;

    /**
     * 简介
     */
    private String intro;

    /**
     * 自我介绍
     */
    private String detail;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    /**
     * 创建者ID
     */
    private String createUserId;

    /**
     * 创建者名称
     */
    private String createUserName;

    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyDate;

    /**
     * 修改者ID
     */
    private String modifyUserId;

    /**
     * 修改者名称
     */
    private String modifyUserName;

    /**
     * 删除时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deleteDate;

    /**
     * 删除者id
     */
    private String deleteUserId;

    /**
     * 删除者名称
     */
    private String deleteUserName;

    /**
     * 删除标记：0：正常 1：删除
     */
    private Integer deleteFlag;

    /**
     * 版本号
     */
    private String version;


    @Override
    public String toString() {
        return "AdminInfo{" + ", adminId=" + adminId + ", adminNo=" + adminNo + ", name=" + name + ", nickName=" + nickName + ", sex=" + sex + ", age=" + age + ", mobile=" + mobile + ", picurl=" + picurl + ", token=" + token + ", pwd=" + pwd + ", lastLoginTime=" + lastLoginTime + ", lastExitTime=" + lastExitTime + ", adminType=" + adminType + ", adminStatus=" + adminStatus + ", intro=" + intro + ", detail=" + detail + ", createDate=" + createDate + ", createUserId=" + createUserId + ", createUserName=" + createUserName + ", modifyDate=" + modifyDate + ", modifyUserId=" + modifyUserId + ", modifyUserName=" + modifyUserName + ", deleteDate=" + deleteDate + ", deleteUserId=" + deleteUserId + ", deleteUserName=" + deleteUserName + ", deleteFlag=" + deleteFlag + ", version=" + version + "}";
    }
}

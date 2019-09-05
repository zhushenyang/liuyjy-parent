package com.example.apiadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.apiadmin.config.RedisUtil;
import com.example.apiadmin.service.AdminService;
import com.example.liuyjyentity.admin.AdminInfo;
import com.example.liuyjyentity.constant.HartConstant;
import com.example.liuyjyentity.constant.RedisConstant;
import com.example.liuyjyentity.response.HartReturnResult;
import com.example.liuyjyentity.response.ResponseParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 介绍：登陆模块
 * Author:liuyjy
 * DATE: 2018/9/4
 * Copy:北京汉艺国际  @ 2018
 */
@Slf4j
@EnableSwagger2
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisUtil redisUtil;


    private int width = 110;//定义图片的width
    private int height = 43;//定义图片的height
    private int codeCount = 4;//定义图片上显示验证码的个数
    private int xx = 15;
    private int fontHeight = 25;
    private int codeY = 30;
    char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @GetMapping(value = "/code")
    public String getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = buffImg.createGraphics();
        //Graphics2D gd = (Graphics2D) buffImg.getGraphics();
//        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);

        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);

        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 40; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        // 将四位数字的验证码保存到Session中。
       /* HttpSession session = req.getSession();
        session.setAttribute("session_code", randomCode.toString());*/
        redisUtil.timeSet(RedisConstant.H_ART_CODE_ + randomCode.toString().toLowerCase(), randomCode.toString().toLowerCase(), TimeUnit.MINUTES);
        log.info("============code!==============" + randomCode.toString());

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
        return null;
    }


    @GetMapping(value = "/getAdminList")
    public ResponseParam getAdminList() {

        int current = HartConstant.CUTTEBT;
        int size = HartConstant.SIZE;
        int onlineStatus = -2;
        Page<AdminInfo> page = new Page<>(current, size);
        IPage<AdminInfo> list = adminService.getAdminList(page);
        return HartReturnResult.ok(list, "ok");
    }

    /**
     * 用户登录
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public ResponseParam login(HttpSession session, @RequestBody Map<String, Object> map) {


        String code = map.get("code").toString().toLowerCase();
        //验证码验证
        String redis_code = redisUtil.getValue(RedisConstant.H_ART_CODE_ + code);
        log.info("============redis_code==============" + redis_code);
        log.info("============code==============" + code);

        map.put("pwd", map.get("pwd").toString().toUpperCase());
        AdminInfo info = adminService.login(map);

        return HartReturnResult.ok(info, "登陆成功");
    }

    /**
     * 退出登陆
     */
    @GetMapping(value = "/exitLogin", produces = "application/json;charset=UTF-8")
    public ResponseParam exitLogin(String token) {

        if (StringUtils.isBlank(token)) {
            return HartReturnResult.ok(null, "退出成功！");
        }

        boolean info = adminService.exitAdmin(token);

        return HartReturnResult.ok(null, "退出成功！");
    }

    /**
     * 修改个人信息
     */
    @PostMapping(value = "/updateMyAdmin", produces = "application/json;charset=UTF-8")
    public ResponseParam updateMyAdmin(@RequestBody AdminInfo info) {

        boolean bool = adminService.updateAdminInfo(info);

        return HartReturnResult.ok(null, "修改成功！");
    }
}

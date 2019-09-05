package com.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;


/**
 * 介绍：代码生成器
 * Author:liuyjy
 * DATE: 2018/9/1
 * Copy:北京汉艺国际  @ 2018
 */
public class CustomGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("F:\\liuyjys");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(true);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("liuyjy");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImap");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);

        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://10.0.192.55:3306/hart-oa?useUnicode=true&characterEncoding=utf-8");
        dsc.setUsername("admin");
        dsc.setPassword("Hart@0213");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        // strategy.setInclude(new String[] { "user" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.yys");
        pc.setModuleName("test");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }

    public static void main2(String[] args) {
        /*String str = "sdswe我是是倒萨";
        int num = 0;
        String array = "";
        for(int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            if((ch+"").getBytes().length == 2){
                array += ch;
            }
        }
        System.out.println(array);
        char[] a = array.toCharArray();
        System.out.println("======="+a);*/

        String str = "1";
        /*int len=str.length();
        String[] result=new String[len];
        for(int i=0;i<len;i++)
        {
            result[i]=str.valueOf(str.charAt(i));
            System.out.println(result[i]);
        }*/

        //String a = "abcd";
        /*String[] strs = new String[str.length()-1];
        for(int i=0;i<str.length()-1;i++){
            strs[i] = str.substring(i, i+2);
            System.out.println(strs[i]);
        }*/

        List list = new ArrayList();
        int i = 0;
        for (i = 0; i < str.length() - 1; i += 2) {
            list.add(str.substring(i, i + 2));
        }
        list.add(str.substring(i));
        for (i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}

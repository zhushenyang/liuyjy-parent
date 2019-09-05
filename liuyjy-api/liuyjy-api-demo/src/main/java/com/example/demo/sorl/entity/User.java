package com.example.demo.sorl.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/27 10:10
 * @Description:
 */
@Slf4j
@Data
public class User implements Serializable {

    //必须实现可序列化接口，要在网络上传输
    @Field("id")//--------使用这个注释，里面的名字是根据你在solr数据库中配置的来决定
    private String id;
    @Field("item_name")
    private String name;
    @Field("item_sex")
    private String sex;
    @Field("item_address")
    private String address;
    @Field("item_host")
    private Integer host;
    @Field("item_text")
    private String text;
}

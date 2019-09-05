package com.example.demo.elasticsearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/5 11:00
 * @Description:
 */
@Data
@ToString
@NoArgsConstructor
public class EsModel implements Serializable {
    private String id;// id

    private String name;// 姓名

    //private double lat;// 纬度

    private String sex;//性别

    // private double lon;// 经度
    //@GeoPointField
    private GeoPoint location;// hashcode
}

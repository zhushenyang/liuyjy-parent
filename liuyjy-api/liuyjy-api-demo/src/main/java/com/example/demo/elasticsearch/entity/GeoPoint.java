package com.example.demo.elasticsearch.entity;

import lombok.Data;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/5 16:51
 * @Description:
 */
@Data
public class GeoPoint {
    private double lat;// 纬度

    private double lon;// 经度
}

package com.example.demo.elasticsearch.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/26 14:16
 * @Description:参数
 */
@Slf4j
@Data
public class RequestParams implements Serializable {
    private String id;
    private String title;
    private String author;
    private Integer wordCount;
    private Long publishDate;
}

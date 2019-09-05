package com.example.apies.service;

import com.example.apies.entity.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/6 14:25
 * @Description:
 */
public interface CommodityService {
    long count();

    Commodity save(Commodity commodity);

    void delete(Commodity commodity);

    Iterable<Commodity> getAll();

    List<Commodity> getByName(String name);

    Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw);

}

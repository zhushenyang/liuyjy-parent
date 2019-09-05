package com.example.apies.dao;

import com.example.apies.entity.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/6 14:24
 * @Description:
 */
@Repository
public interface CommodityRepository extends ElasticsearchRepository<Commodity, String> {
}

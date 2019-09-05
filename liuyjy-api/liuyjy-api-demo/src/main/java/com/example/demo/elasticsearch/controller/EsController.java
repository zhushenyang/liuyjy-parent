package com.example.demo.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.ElasticsearchUtil;
import com.example.demo.elasticsearch.entity.EsModel;
import com.example.demo.elasticsearch.entity.EsPage;
import com.example.demo.elasticsearch.entity.GeoPoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/5 12:04
 * @Description:
 */
@Slf4j
@RestController
@EnableSwagger2
public class EsController {

    /**
     * 测试索引
     */
    private String indexName = "test_index";

    /**
     * 类型
     */
    private String esType = "external";
    private String[] xings = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏"};
    private String[] sexs = {"男", "女"};

    /**
     * http://127.0.0.1:8080/es/createIndex
     * 创建索引
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createIndex")
    public String createIndex(HttpServletRequest request, HttpServletResponse response) {
        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName, esType);
        } else {
            return "索引已经存在";
        }
        return "索引创建成功";
    }

    /**
     * http://127.0.0.1:8080/es/createIndex
     * 删除索引
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delIndex")
    public String delIndex(HttpServletRequest request, HttpServletResponse response) {
        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            return "索引不存在";
        } else {
            ElasticsearchUtil.deleteIndex(indexName);
        }
        return "索引删除成功";
    }


    /**
     * 插入记录
     *
     * @return
     */
    @RequestMapping("/insertJson")
    public String insertJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", DateUtil.formatDate(new Date()));
        jsonObject.put("age", 25);
        jsonObject.put("name", "j-" + new Random(100).nextInt());
        jsonObject.put("date", new Date());
        String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return id;
    }

    /**
     * 插入记录
     *
     * @return
     */
    @RequestMapping("/insertModel")
    public String insertModel() {
        int cont = 0;
        List<EsModel> cityList = new ArrayList<EsModel>();
        double lat = 39.929986;
        double lon = 116.395645;
        for (int i = 0; i < 100000; i++) {
            double max = 0.00001;
            double min = 0.000001;
            Random random = new Random();
            double s = random.nextDouble() % (max - min + 1) + max;
            DecimalFormat df = new DecimalFormat("######0.000000");
            // System.out.println(s);
            String lons = df.format(s + lon);
            String lats = df.format(s + lat);
            Double dlon = Double.valueOf(lons);
            Double dlat = Double.valueOf(lats);

            int b = (int) Math.floor(Math.random() * xings.length);
            String name = xings[b];
            int a = (int) Math.floor(Math.random() * sexs.length);
            String sex = sexs[a];
            if (sex.equals("男")) {
                name = name + "先生";
            } else {
                name = name + "女士";
            }
            EsModel user = new EsModel();
            user.setId(UUID.randomUUID().toString().replace("-", ""));
            user.setName(name);
            user.setSex(sex);
            //user.setLat(dlat);
            //user.setLon(dlon);
            GeoPoint location = new GeoPoint();
            location.setLat(dlat);
            location.setLon(dlon);
            //double[] location={dlat,dlon};
            user.setLocation(location);
            cityList.add(user);

        }
        for (int i = 0; i < cityList.size(); i++) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(cityList.get(i));
            String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
            cont = i;
            log.info("导入" + cont + "条数据");
        }

        return "成功导入" + cont + "条数据";
    }

    /**
     * 删除记录
     *
     * @return
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            ElasticsearchUtil.deleteDataById(indexName, esType, id);
            return "删除id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 删除记录
     *
     * @return
     */
    @RequestMapping("/deleteData")
    public String deleteData() {
        ElasticsearchUtil.deleteData();
        return "";
    }

    /**
     * 更新数据
     *
     * @return
     */
    @RequestMapping("/update")
    public String update(String id) {
        if (StringUtils.isNotBlank(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("age", 31);
            jsonObject.put("name", "修改");
            jsonObject.put("date", new Date());
            ElasticsearchUtil.updateDataById(jsonObject, indexName, esType, id);
            return "id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 获取数据
     * http://127.0.0.1:8080/es/getData?id=2018-04-25%2016:33:44
     *
     * @param id
     * @return
     */
    @RequestMapping("/getData")
    public String getData(String id) {
        if (StringUtils.isNotBlank(id)) {
            Map<String, Object> map = ElasticsearchUtil.searchDataById(indexName, esType, id, null);
            return JSONObject.toJSONString(map);
        } else {
            return "id为空";
        }
    }

    @RequestMapping("/test")
    public static void testGetNearbyPeople(String index, String type, double lat, double lon) {
        SearchRequestBuilder srb = ElasticsearchUtil.searchData(index, type);
        srb.setFrom(0).setSize(1000);//1000人
        // lon, lat位于谦的坐标，查询距离于谦1米到1000米
//        FilterBuilder builder = geoDistanceRangeFilter("location").point(lon, lat).from("1m").to("100m").optimizeBbox("memory").geoDistance(GeoDistance.PLANE);
        GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(100, DistanceUnit.METERS);
        srb.setPostFilter(location1);
        // 获取距离多少公里 这个才是获取点与点之间的距离的
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        sort.point(lat, lon);
        srb.addSort(sort);
        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        // 搜索耗时
        Float usetime = searchResponse.getTook().getMillis() / 1000f;
        System.out.println("附近的人(" + hits.getTotalHits() + "个)，耗时(" + usetime + "秒)：");
        for (SearchHit hit : searchHists) {
            String name = (String) hit.getSourceAsMap().get("name");
            Object location = hit.getSourceAsMap().get("location");
            // 获取距离值，并保留两位小数点
            BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[0]);
            Map<String, Object> hitMap = hit.getSourceAsMap();
            // 在创建MAPPING的时候，属性名的不可为geoDistance。
            hitMap.put("geoDistance", geoDis.setScale(0, BigDecimal.ROUND_HALF_DOWN));
            System.out.println(name + "的坐标：" + location.toString() + "他距离俺" + hit.getSourceAsMap().get("geoDistance") + DistanceUnit.METERS.toString());
        }


    }

    /**
     * 查询数据
     * 模糊查询
     *
     * @return
     */
    @RequestMapping("/queryMatchData")
    public String queryMatchData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolean matchPhrase = false;
        if (matchPhrase == Boolean.TRUE) {
            //不进行分词搜索
            boolQuery.must(QueryBuilders.matchPhraseQuery("name", "m"));
        } else {
            boolQuery.must(QueryBuilders.matchQuery("name", "m-m"));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.
                searchListData(indexName, esType, boolQuery, 10, "name", null, "name");
        return JSONObject.toJSONString(list);
    }

    /**
     * 通配符查询数据
     * 通配符查询 ?用来匹配1个任意字符，*用来匹配零个或者多个字符
     *
     * @return
     */
    @RequestMapping("/queryWildcardData")
    public String queryWildcardData() {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name.keyword", "j-?466");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 正则查询
     *
     * @return
     */
    @RequestMapping("/queryRegexpData")
    public String queryRegexpData() {
        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("name.keyword", "m--[0-9]{1,11}");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询数字范围数据
     *
     * @return
     */
    @RequestMapping("/queryIntRangeData")
    public String queryIntRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").from(21).to(25));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询日期范围数据
     *
     * @return
     */
    @RequestMapping("/queryDateRangeData")
    public String queryDateRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("date").from("2018-04-25T08:33:44.840Z").to("2019-04-25T10:03:08.081Z"));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询分页
     *
     * @param startPage 第几条记录开始
     *                  从0开始
     *                  第1页 ：http://127.0.0.1:8080/es/queryPage?startPage=0&pageSize=2
     *                  第2页 ：http://127.0.0.1:8080/es/queryPage?startPage=2&pageSize=2
     * @param pageSize  每页大小
     * @return
     */
    @RequestMapping("/queryPage")
    public String queryPage(String startPage, String pageSize) {
        if (StringUtils.isNotBlank(startPage) && StringUtils.isNotBlank(pageSize)) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.rangeQuery("date").from("2018-04-25T08:33:44.840Z").to("2019-04-25T10:03:08.081Z"));
            EsPage list = ElasticsearchUtil.searchDataPage(indexName, esType, Integer.parseInt(startPage), Integer.parseInt(pageSize), boolQuery, null, null, null);
            return JSONObject.toJSONString(list);
        } else {
            return "startPage或者pageSize缺失";
        }
    }
}

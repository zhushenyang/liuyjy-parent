package com.example.demo.sorl.controller;

import com.example.demo.sorl.entity.User;
import com.example.demo.sorl.service.SolrService;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.StringUtils;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.*;

/**
 * @Auther: liuyjy
 * @Date: 2019/4/27 10:18
 * @Description:
 */
@Slf4j
@EnableSwagger2
@RestController
public class SolrController {
    @Autowired
    private SolrService solrService;

    @Autowired
    private SolrClient solrClient;

    private static final String CORE = "mycore";

    //批量增加
    @GetMapping("/addUsers")
    public void addUsers() throws IOException, SolrServerException {
        List<User> users = solrService.addUser();
        /*
         * 如果 spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数 下面都是一样的 即
         * client.commit();
         */
        solrClient.addBeans(CORE, users);
        solrClient.commit(CORE);
    }

    //单个增加
    @GetMapping("/addUser")
    public void addUser() throws IOException, SolrServerException {
        User user = new User();
        user.setId("456788");
        user.setName("王强");
        user.setAddress("北京市");
        user.setSex("女");
        user.setHost(456752);
        user.setText("富文本");
        solrClient.addBean(CORE, user);
        solrClient.commit(CORE);
    }

    //根据di查询
    @GetMapping("/getByIdFromSolr/{id}")
    public String getByIdFromSolr(@PathVariable("id") String id) throws IOException, SolrServerException {


        //根据id查询内容
        SolrDocument solrDocument = solrClient.getById(CORE, id);
        //获取filedName
        Collection<String> fieldNames = solrDocument.getFieldNames();
        //获取file名和内容
        Map<String, Object> fieldValueMap = solrDocument.getFieldValueMap();

//            int childDocumentCount = solrDocument.getChildDocumentCount();

        List<SolrDocument> childDocuments = solrDocument.getChildDocuments();

        System.out.println("byId==================" + solrDocument);
        System.out.println("fieldNames==================" + fieldNames);
        System.out.println("fieldValueMap==================" + fieldValueMap);
//            System.out.println("childDocumentCount=================="+childDocumentCount);
        System.out.println("childDocuments==================" + childDocuments);
        return solrDocument.toString();

    }

    //根据di删除
    @GetMapping("/delById/{id}")
    public String delById(@PathVariable("id") String id) throws IOException, SolrServerException {
        try {
            //根据id删除信息
            UpdateResponse updateResponse = solrClient.deleteById(CORE, id);
            //执行的时间
            long elapsedTime = updateResponse.getElapsedTime();

            int qTime = updateResponse.getQTime();
            //请求地址
            String requestUrl = updateResponse.getRequestUrl();
            //请求的结果{responseHeader={status=0,QTime=2}}
            NamedList<Object> response = updateResponse.getResponse();
            //请求结果的头{status=0,QTime=2}
            NamedList responseHeader = updateResponse.getResponseHeader();
            //请求的状态 0
            int status = updateResponse.getStatus();

            System.out.println("elapsedTime===========" + elapsedTime);
            System.out.println("qTime===========" + qTime);
            System.out.println("requestUrl===========" + requestUrl);
            System.out.println("response===========" + response);
            System.out.println("responseHeader===========" + responseHeader);
            System.out.println("status===========" + status);
            solrClient.commit(CORE);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 4、删 all
     *
     * @return
     */
    @DeleteMapping("deleteAll")
    public String deleteAll() {
        try {
            solrClient.deleteByQuery(CORE, "*:*");
            solrClient.commit(CORE);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 5、改
     *
     * @param user 用户信息
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    @PutMapping("/update")
    public String update(User user) throws IOException, SolrServerException {
        try {
            if (user == null) {
                return "user is null";
            }
           /* SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", id);
            doc.setField("text", message);*/

            /*
             * 如果 spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 itaem 这个参数 下面都是一样的 即
             * client.commit();
             */
            solrClient.addBean(CORE, user);
            solrClient.commit(CORE);
            return user.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 6、全
     *
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    @GetMapping("/get/all")
    public Map<String, Object> getAll() throws SolrServerException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }

    /**
     * 7、查  ++:关键字、高亮、分页  ✔
     *
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    @GetMapping("/select/{q}/{page}/{size}")
    public Map<String, Object> select(@PathVariable String q, @PathVariable Integer page, @PathVariable Integer size) throws SolrServerException, IOException {
        SolrQuery params = new SolrQuery();

        //查询条件, 这里的 q 对应
        StringBuffer buffer = new StringBuffer();
        if (!StringUtils.isEmpty(q)) {
            buffer.append("item_text:" + q + " OR ");
            buffer.append("item_name:" + q + " OR ");
            buffer.append("item_address:" + q);
        } else {
            buffer.append("*:*");
        }
        params.set("q", buffer.toString());

        /**
         * *:*:表示查询全部 *Mac*代表左右模糊匹配，如果写Mac代表绝对匹配
         */
        //params.set("q", "item_name:Mac*");

        //filer query 过滤查询，相当于 sql语句的 where（根据指定条件查询）
        params.set("fq", "item_host:[100 TO 100000]");
        //params.set("fq", "item_name:*"+q+"*");

        /**
         * 过滤条件:电脑办公类，10000元以上 [A TO B] 范围A到B之间 [A TO *] A到无穷 [* TO B] B以下
         * 相当于sql语句中的where ----->fq = filter query
         */
        //params.set("fq", "item_cat_name:电脑办公类");
        //params.set("fq", "item_price:[10000 TO *]");

        //根据哪个字段进行排序
        params.addSort("id", SolrQuery.ORDER.asc);
        params.addSort("item_host", SolrQuery.ORDER.asc);

        // 分页
        params.setStart(page);
        params.setRows(size);

        //默认域
        params.set("df", "item_text");

        //显示哪些字段
        //params.set("fl", "id,item_name,item_sex,item_address,item_host,item_text");

        //高亮
        //打开开关
        params.setHighlight(true);
        //highLight,是否高亮，选择既是高亮，随后设置高亮字段，多个需要高亮的字段逗号隔开
        params.addHighlightField("item_text,item_name,item_host");
        //设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        //设置后缀
        params.setHighlightSimplePost("</span>");


        // solr数据库是 itaem
        QueryResponse queryResponse = solrClient.query(CORE, params);
        SolrDocumentList results = queryResponse.getResults();

        // 数量，分页用
        long total = results.getNumFound();// JS 使用 size=MXA 和 data.length 即可知道长度了（但不合理）

        // 获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
        Map<String, Map<String, List<String>>> highlightList = queryResponse.getHighlighting();

        /**
         * 静态html资源里面的对象 -- ${list}
         */
        List<User> list = new ArrayList<>();
        // 返回高亮之后的结果..
        for (SolrDocument solrDocument : results) {
            //id,item_name,item_sex,item_address,item_host,item_text
            String id = solrDocument.getFirstValue("id").toString();
            String item_name = solrDocument.getFirstValue("item_name").toString();
            String item_sex = solrDocument.getFirstValue("item_sex").toString();
            String item_address = solrDocument.getFirstValue("item_address").toString();
            String item_host = solrDocument.getFirstValue("item_host").toString();
            String item_text = solrDocument.getFirstValue("item_text").toString();
            System.err.println(id);
            Map<String, List<String>> fieldMap = highlightList.get(solrDocument.get("id"));
            if (fieldMap.containsKey("item_text")) {
                List<String> textlist = fieldMap.get("item_text");
                item_text = textlist.get(0);
            }
            if (fieldMap.containsKey("item_name")) {
                List<String> namelist = fieldMap.get("item_name");
                item_name = namelist.get(0);
            }
            if (fieldMap.containsKey("item_host")) {
                List<String> hostlist = fieldMap.get("item_host");
                item_host = hostlist.get(0);
            }
            //System.err.println(item_price);
            User user = new User();
            user.setId(id);
            user.setName(item_name);
            user.setAddress(item_address);
            user.setSex(item_sex);
            user.setHost(Integer.parseInt(item_host));
            user.setText(item_text);
            list.add(user);
            System.err.println("============分割线==============");
        }
        System.err.println("查询到的总条数:" + results.getNumFound() + ", 内容:" + results);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("data", list);
        return map;

    }

    @PostMapping("/queryFromSolr")
    public Object queryFromSolr() throws IOException, SolrServerException {
        //第一种方式
//        Map<String, String> queryParamMap = new HashMap<String, String>();
//        queryParamMap.put("q", "*:*");
//        queryParamMap.put("f1","id,name");
//        queryParamMap.put("sort","id asc");
//        MapSolrParams mapSolrParams = new MapSolrParams(queryParamMap);
//        solrClient.query(mapSolrParams);

        //第二种方式
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
//        solrQuery.addField("*");
        solrQuery.add("q", "id:4567");

        solrQuery.setSort("id", SolrQuery.ORDER.asc);
        //设置查询的条数
        solrQuery.setRows(50);
        //设置查询的开始
        solrQuery.setStart(0);
        //设置高亮
        solrQuery.setHighlight(true);
        //设置高亮的字段
        solrQuery.addHighlightField("item_name");
        //设置高亮的样式
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        System.out.println(solrQuery);
        QueryResponse response = solrClient.query(solrQuery);
        //返回高亮显示结果
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        log.info("高亮显示结果==" + highlighting);
        //response.getResults();查询返回的结果
        SolrDocumentList documentList = response.getResults();
        for (SolrDocument solrDocument : documentList) {
            System.out.println("solrDocument==============" + solrDocument);
        }
        return documentList;


    }

}

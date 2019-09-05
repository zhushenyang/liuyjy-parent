package com.example.apimongodb.dao;

import com.example.apimongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.regex.Pattern;

/**
 * @Auther: liuyjy
 * @Date: 2019/5/7 10:13
 * @Description:
 */
@Repository
public class userDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     */
    public void saveTest(User test) {
        mongoTemplate.save(test);
    }

    /**
     * 根据用户名查询对象
     *
     * @return
     */
    public User findTestByName(String name) {
        Query query = new Query();
        Criteria criteria = new Criteria();

    /**
     * 模糊查询
     * 这里使用的正则表达式的方式
     * 第二个参数Pattern.CASE_INSENSITIVE是对字符大小写不明感匹配
     * Pattern pattern = Pattern.compile("^.*" + 这里拼接你的查询条件字符串 + ".*$", Pattern.CASE_INSENSITIVE);
     */
        //
    /**
     *  criteria.and().regex() 在这里是构建了一个模糊查询的条件，并且用 'and' 相连
     *  query.addCriteria 把条件封装起来
     *  query.addCriteria(criteria.and("这里填写你MongoDB集合中的key").regex(pattern));
     */
        //

    /**
     * 精确查询
     *  criteria.and().is() 在这里是构建了一个精准查询的条件，并且用 'and' 相连
     *  query.addCriteria 把条件封装起来
     *  query.addCriteria(criteria.and("你MongoDB中的key").is("你的条件");
     */

        //大于方法
        //Criteria gt = Criteria.where("你MongoDB中的key").gt("你的条件");
        //小于方法
       // Criteria lt = Criteria.where("你MongoDB中的key").lt("你的条件");
    /**
     * new Criteria().andOperator(gt,lt) 把上面的大于和小于放在一起，注意上面两个key一定是一样的
     * andOperator() 这个方法就是关键的方法了，把同key的多个条件组在一起
     * 但是 andOperator() 有个很坑的地方就是，在一个query中只能出现一次
     * 如果你有很固定很明确的入参，那还好，直接调用一次 andOperator()
     * 如果是多个key需要多个条件，而且条件数量还是动态的，怕不是魔鬼吧...
     * query.addCriteria(new Criteria().andOperator(gt,lt));
     */


        criteria.where("name").is(name);
        query.addCriteria(criteria);
        //Query query = new Query(Criteria.where("name").is(name));
        User mgt = mongoTemplate.findOne(query, User.class);
        return mgt;
    }

    /**
     * 更新对象
     */
    public void updateTest(User test) {
        Query query = new Query(Criteria.where("id").is(test.getId()));
        Update update = new Update().set("age", test.getAge()).set("name", test.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,TestEntity.class);
    }

    /**
     * 删除对象
     *
     * @param id
     */
    public void deleteTestById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }
}

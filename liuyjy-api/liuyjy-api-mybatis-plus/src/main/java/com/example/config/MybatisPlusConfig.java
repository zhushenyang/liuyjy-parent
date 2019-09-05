package com.example.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.example.enums.DataSourceEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan("com.example.mapper")//这个注解，作用相当于下面的@Bean MapperScannerConfigurer，2者配置1份即可
public class MybatisPlusConfig {

    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        //paginationInterceptor.setLocalPage(true);
        page.setDialectType("mysql");
        return page;
    }


    @Bean(name = "dbmaster")
    @ConfigurationProperties(prefix = "spring.datasource.druid.dbmaster" )
    public DataSource dbmaster() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dbslave1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.dbslave1" )
    public DataSource dbslave1() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     * @return
     */
    @Primary
    @Bean
    public DataSource multipleDataSource(@Qualifier("dbslave1") DataSource dbslave1, @Qualifier("dbmaster") DataSource dbmaster) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.DB_MASTER.getValue(), dbmaster);
        targetDataSources.put(DataSourceEnum.DB_SLAVE1.getValue(), dbslave1);
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(dbmaster);
        return multipleDataSource;
    }

    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}

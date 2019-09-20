package com.jinglitong.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.jinglitong.shop.datasource.DynamicDataSourceConfig;

import tk.mybatis.spring.annotation.MapperScan;


/**
 * @ClassName ShopProcessApp
 * @Description 同步接口启动入口
 * @Author zili.zong
 * @Date 2019/1/7 13:47
 * @Version 1.0
 **/
@Import(DynamicDataSourceConfig.class)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@MapperScan("com.jinglitong.shop.mapper")
@EnableScheduling
public class ShopProcessApp {
    public static void main(String[] args){
        SpringApplication.run(ShopProcessApp.class,args);
        System.out.println("ShopProcessApp started....merge222");
    }
}

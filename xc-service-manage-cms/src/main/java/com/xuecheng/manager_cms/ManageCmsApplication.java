package com.xuecheng.manager_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * 为什么要在这个地方加注解呢
 * 因为我们在实体类中加上了Swagger相关注解
 * 为了扫描到相关注解，就加上一个@EntityScan注解
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms")//扫描实体类
@ComponentScans({
        @ComponentScan(basePackages = {
                "com.xuecheng.api"//扫描接口

        }),
        @ComponentScan(basePackages = {
                "com.xuecheng.manager_cms" //扫描本类下的所有包
        })
}

)
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class,args);
    }
}

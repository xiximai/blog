package com.lrj.myblogmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("com.lrj.myblogmybatis.dao")
@EnableScheduling
public class MyblogmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogmybatisApplication.class, args);
    }

}

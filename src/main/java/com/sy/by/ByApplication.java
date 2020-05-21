package com.sy.by;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@MapperScan("com.sy.by.mapper")
@SpringBootApplication
public class ByApplication{

    //入口
    public static void main(String[] args) {
        SpringApplication.run(ByApplication.class, args);
    }
}

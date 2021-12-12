package com.koolyun.ysf.ysfdemo;


import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.koolyun.ysf.ysfdemo.dao")
public class YsfDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YsfDemoApplication.class, args);
    }

}

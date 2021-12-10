package com.koolyun.ysf.ysfdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YsfDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YsfDemoApplication.class, args);
    }

}

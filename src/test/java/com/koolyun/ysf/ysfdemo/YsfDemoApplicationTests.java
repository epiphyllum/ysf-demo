package com.koolyun.ysf.ysfdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@SpringBootTest
class YsfDemoApplicationTests {

    @Resource(name = "taskExecutor")
    private Executor executor;

    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
                executor.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
        }
    }

}

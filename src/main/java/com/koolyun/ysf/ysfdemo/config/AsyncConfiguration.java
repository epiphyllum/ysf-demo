package com.koolyun.ysf.ysfdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean("taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);//核心线程数
        taskExecutor.setMaxPoolSize(50); //最大线程数
        taskExecutor.setQueueCapacity(200);//等待队列
        taskExecutor.setKeepAliveSeconds(60);//超时时间
        taskExecutor.setThreadNamePrefix("taskExecutor");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);//等待所有任务结束在关闭线程池
        taskExecutor.setAwaitTerminationSeconds(60);
        return taskExecutor;
    }
}

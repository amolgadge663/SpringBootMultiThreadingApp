package com.amolsoftwares.www.mymultithreadingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Async
@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(2);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setThreadNamePrefix("User Thread -- ");
        taskExecutor.initialize();
        return taskExecutor;
    }
}

package com.javangarda.fantacalcio.user;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableIntegration
@IntegrationComponentScan(basePackages={"com.javangarda.fantacalcio.user"})
@Configuration
@EnableBinding(Source.class)
public class IntegrationConfig implements AsyncConfigurer {

    @Bean
    public MessageChannel userRegisteredChannel() {
        return new PublishSubscribeChannel(getAsyncExecutor());
    }

    @Bean
    public MessageChannel emailConfirmedChannel() {
        return new PublishSubscribeChannel(getAsyncExecutor());
    }

    @Bean
    public MessageChannel sendingConfirmationEmailChannel() {
        return new PublishSubscribeChannel(getAsyncExecutor());
    }

    @Bean
    public MessageChannel userBannedChannel() {
        return new PublishSubscribeChannel(getAsyncExecutor());
    }

    @Bean
    public MessageChannel createActivationEmailTokenChannel() {
        return new PublishSubscribeChannel(getAsyncExecutor());
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(50);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}

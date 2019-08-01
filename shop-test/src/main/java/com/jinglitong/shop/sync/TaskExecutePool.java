package com.jinglitong.shop.sync;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class TaskExecutePool {

	
	@Bean
	public Executor myTaskAsyncPool() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(2000);
		executor.setQueueCapacity(1000);
		executor.setKeepAliveSeconds(300);
		executor.setThreadNamePrefix("MyExecutor-");
 
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}
	/*
	 * corePoolSize:表示线程池核心线程，正常情况下开启的线程数量。
	 * 
	 * queueCapacity：当核心线程都在跑任务，还有多余的任务会存到此处。
	 * 
	 * maxPoolSize：如果queueCapacity存满了，还有任务就会启动更多的线程，直到线程数达到maxPoolSize。如果还有任务，
	 * 则根据拒绝策略进行处理。
	 * 
	 * 拒绝策略有多种：
	 * 
	 * 由任务调用线程执行 抛异常 多余的直接抛弃 根据FIFO（先进先出）抛弃队列里任务
	 * 
	 */
}

package com.jinglitong.shop.controller;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("等待子线程执行完毕...");
		CountDownLatch countDownLatch = new CountDownLatch(2);
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("子线程," + Thread.currentThread().getName() + "开始执行...");
				System.out.println("子线程," + Thread.currentThread().getName() + "结束执行...");
				countDownLatch.countDown();// 每次减去1
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("子线程," + Thread.currentThread().getName() + "开始执行...");
				System.out.println("子线程," + Thread.currentThread().getName() + "结束执行...");
				countDownLatch.countDown();
			}
		}).start();

		countDownLatch.await();// 调用当前方法主线程阻塞  countDown结果为0, 阻塞变为运行状态
		System.out.println("两个子线程执行完毕....");
		System.out.println("继续主线程执行..");
	}

}
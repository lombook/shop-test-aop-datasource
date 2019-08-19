package com.jinglitong.shop.controller;

import java.util.concurrent.TimeUnit;

public class DaemonThread {

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(()->System.out.println("JVM 退出了")));
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println("我是子线程...");
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
		for (int i = 0; i < 5; i++) {
			try {
				//TimeUnit.SECONDS.sleep(100);
				Thread.sleep(100);
			} catch (Exception e) {
			}
			System.out.println("我是主线程"+i);
		}
		
		System.out.println("主线程执行完毕!");
	}
}

package com.jinglitong.shop.controller;

/**
 * 
 * Copyright (c) 2019, 井立通 All rights reserved. 文件名称: Test.java 作 者: yxl
 * 2019年9月2日 创建时间: 2019年9月2日 功能说明:自旋锁
 */
public class Test implements Runnable {
	static int sum;
	private SpinLock lock;

	public Test(SpinLock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		this.lock.lock();
		// this.lock.lock();
		sum++;
		// this.lock.unlock();
		this.lock.unlock();
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		SpinLock lock = new SpinLock();
		for (int i = 0; i < 100; i++) {
			Test test = new Test(lock);
			Thread t = new Thread(test);
			t.start();
		}

		Thread.currentThread().sleep(1000);
		System.out.println(sum);
	}

}
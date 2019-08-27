package com.jinglitong.shop.controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Parent implements Runnable {
	private String name;
	private Semaphore wc;
	public Parent(String name,Semaphore wc){
		this.name=name;
		this.wc=wc;
	}
	@Override
	public void run() {
		try {
			// 剩下的资源(剩下的茅坑)
			int availablePermits = wc.availablePermits();
			if (availablePermits > 0) {
				System.out.println(name+"天助我也,终于有茅坑了...");
			} else {
				System.out.println(name+"怎么没有茅坑了...");
			}
			//申请茅坑 如果资源达到3次，就等待
			wc.acquire();
			System.out.println(name+"终于轮我上厕所了..爽啊");
			Thread.sleep(new Random().nextInt(1000)); // 模拟上厕所时间。
			System.out.println(name+"厕所上完了...");
			
		} catch (Exception e) {

		}finally {
			wc.release();
		}
	}
}
public class SemaphoreTest {
	public static void main(String[] args) {
		// 一个厕所只有3个坑位，但是有10个人来上厕所，那怎么办？假设10的人的编号分别为1-10，并且1号先到厕所，10号最后到厕所。那么1-3号来的时候必然有可用坑位，顺利如厕，4号来的时候需要看看前面3人是否有人出来了，如果有人出来，进去，否则等待。同样的道理，4-10号也需要等待正在上厕所的人出来后才能进去，并且谁先进去这得看等待的人是否有素质，是否能遵守先来先上的规则。
         Semaphore semaphore = new Semaphore(3);
		for (int i = 1; i <=10; i++) {
			 Parent parent = new Parent("第"+i+"个人,",semaphore);
			 new Thread(parent).start();
		}
	}
}

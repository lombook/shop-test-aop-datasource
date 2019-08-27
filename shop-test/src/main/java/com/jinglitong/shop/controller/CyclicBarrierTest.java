package com.jinglitong.shop.controller;

import java.util.concurrent.CyclicBarrier;

class Writer extends Thread {
	private CyclicBarrier cyclicBarrier;
	public Writer(CyclicBarrier cyclicBarrier){
		 this.cyclicBarrier=cyclicBarrier;
	}
	@Override
	public void run() {
		System.out.println("线程" + Thread.currentThread().getName() + ",正在写入数据");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("线程" + Thread.currentThread().getName() + ",写入数据成功.....");
		
		try {
			cyclicBarrier.await();
		} catch (Exception e) {
		}
		System.out.println("所有线程执行完毕..........");
	}

}

public class CyclicBarrierTest {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
		for (int i = 0; i < 5; i++) {
			Writer writer = new Writer(cyclicBarrier);
			writer.start();
		}
	}

}

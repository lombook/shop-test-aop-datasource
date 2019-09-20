package com.jinglitong.shop.controller;

class ThreadVolatileDemo extends Thread {
	public volatile boolean flag = true;

	@Override
	public void run() {
		System.out.println("开始执行子线程....");
		while (flag) {
		}
		System.out.println("线程停止");
	}

	public void setRuning(boolean flag) {
		this.flag = flag;
	}

}

public class ThreadVolatile {
	/**
	 * 
	 * 功能说明: 当没有加volatile的时候，
	 * 子线程flag = true；
	 * 当主线程修改flag = false的时候，
	 * 子线程的flag没有被修改，还是用
	 * 的私有内存数据，导致程序一直处于while循环中，
	 * 加上volatile之后flag值变了，
	 * 私有内存数据失效，得从主内存读取最新数据，
	 * 所以循环结束。
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
		threadVolatileDemo.start();
		Thread.sleep(3000);
		threadVolatileDemo.setRuning(false);
		System.out.println("flag 已经设置成false");
		Thread.sleep(1000);
		System.out.println(threadVolatileDemo.flag);
	}
}
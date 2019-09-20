package com.jinglitong.shop.MultithreadCommunication;

import com.jinglitong.shop.entity.Breads;

public class TestBreads {

	 public static void main(String[] args) {
	        
	        //new一个面包类
	        Breads bre = new Breads();
	        
	        //new一个生产者类
	        producer proth = new producer(bre);
	        //new一个消费者类
	        consume conth = new consume(bre);
	        
	        //new一个包含消费者类的线程
	        Thread t1 = new Thread(proth,"生产者");
	        
	        //new一个包含生产者类的线程
	        Thread t2 = new Thread(conth,"消费者");
	        
	        //启动线程
	        t1.start();
	        t2.start();
	        
	    }
}

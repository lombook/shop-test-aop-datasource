package com.jinglitong.shop.controller;
public class JvmDemo04 {
	 private static int count;
	 public static void count(){
		try {
			 count++;
			 count(); 
		} catch (Throwable e) {
			System.out.println("最大深度:"+count);
			e.printStackTrace();
		}
	 }
	 public static void main(String[] args) {
		 count();
	}
}

package com.jinglitong.shop.controller;
/**
 * 
 * Copyright (c) 2019, 井立通
 * All rights reserved.
 * 文件名称: FinallyTest.java
 * 作        者: yxl 2019年9月28日
 * 创建时间: 2019年9月28日
 * 功能说明: return 之后finally也会执行
 */
public class FinallyTest {

	public static String get() {
		try {
			System.out.println("try");
			//System.exit(0);
			int a = 1/0;
		} catch (Exception e) {
			 System.out.println("Exception");
			 return "2";
		}finally {
			System.out.println("finally");
			return "3";
		}
	}
	public static void main(String[] args) {
		System.out.println(get());
	}
}

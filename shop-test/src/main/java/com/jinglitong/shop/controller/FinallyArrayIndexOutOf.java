package com.jinglitong.shop.controller;

/**
 * 
 * Copyright (c) 2019, 井立通
 * All rights reserved.
 * 文件名称: FinallyArrayIndexOutOf.java
 * 作        者: yxl 2019年9月28日
 * 创建时间: 2019年9月28日
 * 功能说明:没有再try中报异常，不会执行finally
 */
public class FinallyArrayIndexOutOf {

	public static void main(String[] args) {
		 
		System.out.println("main 代码块中的执行结果为：" + myMethod());
	}
 
	public static int myMethod() {
 
		int i = 0;
		int[] num = { 1, 2, 3 };
 
		System.out.println(num[3]);
 
		try {
			System.out.println("try 代码块被执行！");
			return 0;
		} catch (Exception e) {
			System.out.println("catch 代码块被执行！");
			return 0;
		} finally {
			System.out.println("finally 代码块被执行！");
		}
 
	}
}

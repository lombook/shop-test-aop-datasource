package com.jinglitong.shop.controller;
public class JVMDemo05 {
	public static void main(String[] args) {
		JVMDemo05 jvmDemo05 = new JVMDemo05();
		jvmDemo05 = null;
		System.gc();
	}
	protected void finalize() throws Throwable {
       System.out.println("gc在回收对象...");
	}
}
package com.jinglitong.shop.controller;

import java.util.ArrayList;
import java.util.List;

public class JvmDemo02 {


	//-Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
public static void main(String[] args) throws InterruptedException {
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			System.out.println("i:"+i);
			Byte [] bytes=	new Byte[1*1024*1024];
			list.add(bytes);
		}
		System.out.println("添加成功...");
	}
}

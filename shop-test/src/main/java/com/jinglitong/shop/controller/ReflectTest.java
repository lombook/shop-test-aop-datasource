package com.jinglitong.shop.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 
 * Copyright (c) 2019, 井立通
 * All rights reserved.
 * 文件名称: ReflectTest.java
 * 作        者: yxl 2019年9月4日
 * 创建时间: 2019年9月4日
 * 功能说明:利用反射把String和int放到arrylist里面
 * 链接：https://www.nowcoder.com/questionTerminal/203b16053654416c905721828160bf96?orderByHotValue=1&page=1&onlyReference=false
来源：牛客网

1、存储内容比较： Array 数组可以包含基本类型和对象类型， ArrayList 却只能包含对象类型。
 Array 数组在存放的时候一定是同种类型的元素。ArrayList 就不一定了 。 
2、空间大小比较： Array 数组的空间大小是固定的,所以需要事前确定合适的空间大小。 ArrayList 的空间是动态增长的,
而且，每次添加新的元素的时候都会检查内部数组的空间是否足够。
3.方法上的比较： ArrayList 方法上比 Array 更多样化，比如添加全部 addAll()、删除全部 removeAll()、返回迭代器 iterator() 等。
 */
public class ReflectTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<String> list = new ArrayList<>();
		list.add("發士大夫11");
		list.add("11大撒大撒");
		Class<?> class1 = list.getClass();
		Method method = class1.getMethod("add",Object.class);
		method.invoke(list, 100);
		
		for (Object o : list) {
			System.out.println(o);
			System.out.println(o instanceof String);
		}
		
		Integer [] arry = new Integer[11];
		 
		
		
	}
}

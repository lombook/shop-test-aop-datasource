package com.jinglitong.shop.daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
	private Object tarjet;

	public JDKProxy(Object tarjet) {
		this.tarjet = tarjet;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我是房产中介.....开始监听你买房啦!");
		Object oj = method.invoke(tarjet, args);
		System.out.println("我是房产中介.....结束监听你买房啦!");
		return oj;

	}

	public static void main(String[] args) {
		XiaoMing xiaoMing = new XiaoMing();
		JDKProxy jdkProxy = new JDKProxy(xiaoMing);
		Hose hose=(Hose) Proxy.newProxyInstance(xiaoMing.getClass().getClassLoader(), xiaoMing.getClass().getInterfaces(), jdkProxy);
		hose.mai();
		hose.mai2();
	}
}


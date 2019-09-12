package com.jinglitong.shop.daili;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class Cglib implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("我是买房中介 ， 开始监听你买房了....");
		Object invokeSuper = methodProxy.invokeSuper(o, args);
		System.out.println("我是买房中介 ， 开结束你买房了....");
		return invokeSuper;

	}
	public static void main(String[] args) {
		Cglib cglib = new Cglib();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(XiaoMing.class);
		enhancer.setCallback(cglib);
		Hose hose = (Hose) enhancer.create();
		hose.mai();
		hose.mai2();
	}
}


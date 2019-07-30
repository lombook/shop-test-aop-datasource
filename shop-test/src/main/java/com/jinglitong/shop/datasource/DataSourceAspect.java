package com.jinglitong.shop.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 多数据源，切面处理类 处理带有注解的方法类
 * Copyright (c) 2019, 井立通
 * All rights reserved.
 * 文件名称: DataSourceAspect.java
 * 作        者: yxl 2019年7月30日
 * 创建时间: 2019年7月30日
 * 功能说明:
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered{

	 protected Logger logger = LoggerFactory.getLogger(getClass());
	 
	    @Pointcut("@annotation(com.jinglitong.shop.datasource.DataSource)")//这个注解DataSource的包名
	    public void dataSourcePointCut() {
	 
	    }
	 
	    @Around("dataSourcePointCut()")
	    public Object around(ProceedingJoinPoint point) throws Throwable {
	        MethodSignature signature = (MethodSignature) point.getSignature();
	        Method method = signature.getMethod();
	 
	        DataSource ds = method.getAnnotation(DataSource.class);
	        if(ds == null){
	            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
	            System.out.println("FIRST");
	            logger.debug("set datasource is " + DataSourceNames.FIRST);
	        }else {
	        	System.out.println(ds.name());
	            DynamicDataSource.setDataSource(ds.name());
	            logger.debug("set datasource is " + ds.name());
	        }
	 
	        try {
	            return point.proceed();
	        } finally {
	            DynamicDataSource.clearDataSource();
	            logger.debug("clean datasource");
	        }
	    }
	 
	    @Override
	    public int getOrder() {
	        return 1;
	    }
}

package com.jinglitong.shop.log;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

	@Pointcut("execution(public * com.jinglitong.shop.controller.*.*(..))")
	public void log() {}
	
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes Attributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = Attributes.getRequest();
		log.info("url:{}",request.getRequestURL().toString());
		log.info("http_method",request.getMethod());
		log.info("ip",request.getRemoteAddr());
		Enumeration<String>enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = enu.nextElement();
			log.info("name:{},vale:{}",name,request.getParameter(name));
		}
	}
	
	@AfterReturning(returning ="ret",pointcut="log()")
	public void doAfterReturning(Object ret) {
		log.info("response:{}",ret);
	}
}

package com.jinglitong.shop.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 1、捕获返回错误json 2.捕获返回错误页面 Copyright (c) 2019, 井立通 All rights reserved. 文件名称:
 * GlobalExceptionHandler.java 作 者: yxl 2019年7月31日 创建时间: 2019年7月31日 功能说明:
 */
@ControllerAdvice(basePackages = "com.jinglitong.shop.controller")
public class GlobalExceptionHandler {
 
	
	/**
	 * 请求参数错误
	 */
	private final static String BASE_PARAM_ERR_CODE = "BASE-PARAM-01";
	private final static String BASE_PARAM_ERR_MSG = "参数校验不通过";
	/**
	 * 无效的请求
	 */
	private final static String BASE_BAD_REQUEST_ERR_CODE = "BASE-PARAM-02";
	private final static String BASE_BAD_REQUEST_ERR_MSG = "无效的请求";

	// modelandview
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> errorResult(Exception e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 500);
		map.put("msg", "系统错误");
		map.put("data", e);
		return map;
	}

	/**
	 * 缺少servlet请求参数抛出的异常
	 *
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public Map<String, Object> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", BASE_PARAM_ERR_CODE);
		map.put("msg", BASE_PARAM_ERR_MSG);
		return map;
	}

	/**
	 * 方法请求参数类型不匹配异常
	 *
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public Map<String, Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", BASE_PARAM_ERR_CODE);
		map.put("msg", BASE_PARAM_ERR_MSG);
		return map;
	}

	/**
	 * 请求参数绑定到controller请求参数时的异常
	 *
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BindException.class })
	public Map<String, Object> handleHttpMessageNotReadableException(BindException e) {
		BindingResult result = e.getBindingResult();
		String message = getBindResultMessage(result);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", BASE_PARAM_ERR_CODE);
		map.put("msg", message);
		return map;

	}

	private String getBindResultMessage(BindingResult result) {
		FieldError error = result.getFieldError();
		String field = error != null ? error.getField() : "空";
		String code = error != null ? error.getDefaultMessage() : "空";
		return String.format("%s:%s", field, code);
	}

	/**
	 * 不支持该请求方法时抛出的异常
	 *
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public Map<String, Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", BASE_PARAM_ERR_CODE);
		map.put("msg", BASE_PARAM_ERR_MSG);
		return map;
	}

}

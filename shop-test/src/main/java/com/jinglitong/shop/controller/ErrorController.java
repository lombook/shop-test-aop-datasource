package com.jinglitong.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

	@RequestMapping("/getInt")
	public String getInt(int i) {
		int j = 1/i;
		return "sucees"+j;
	}
}

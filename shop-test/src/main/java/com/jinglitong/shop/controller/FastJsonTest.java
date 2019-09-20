package com.jinglitong.shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinglitong.shop.entity.Club;

public class FastJsonTest {

	static String jsonStr = "{\"sites\":[{\"name\":\"的接口是楼顶\",\"url\":\"大叔大婶多\"},{\"name\":\"打法\",\"url\":\"额外全额群翁二\"}]}";

	public static void main(String[] args) {
		Club c = JSON.parseObject(jsonStr, Club.class);
		System.out.println(c.getSites().size());
		JSONObject jsonStrObject  = JSON.parseObject(jsonStr);
		JSONArray jsonArray = jsonStrObject.getJSONArray("sites");
		for (Object object : jsonArray) {
			JSONObject stObject = (JSONObject) object;
			String name = stObject.getString("name");
			String url = stObject.getString("url");
			System.out.println(name + "---" + url);
		}
	}
}

package com.jinglitong.shop.entity;

import java.util.List;

public class Club {

	private List<sites> sites;

	public List<sites> getSites() {
		return sites;
	}

	public void setSites(List<sites> sites) {
		this.sites = sites;
	}
	
	
}


class sites {

	private String name;
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

package com.jinglitong.shop.entity;

import java.util.List;

class Item {
	private String itemId;
	private String itemName;

	public String getItemId() {

		return itemId;
	}

	public void setItemId(String itemId) {

		this.itemId = itemId;
	}

	public String getItemName() {

		return itemName;
	}

	public void setItemName(String itemName) {

		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + "]";
	}

}

public class User {

	private String id;
	private String name;
	private List<Item> items;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public List<Item> getItems() {

		return items;
	}

	public void setItems(List<Item> items) {

		this.items = items;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", items=" + items + "]";
	}

}

package com.promo.clients;

import com.promo.type.Price_list;

public class Chanel {
	private String Name;
	private Price_list price_list;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Price_list getPrice_list() {
		return price_list;
	}
	public void setPrice_list(Price_list price_list) {
		this.price_list = price_list;
	}
}

package com.promo.type;

import java.util.HashMap;
import java.util.Map;

public class Price_list {
	private Map<String, Integer> list = new HashMap<String, Integer>();//String - время; Integer - стоимость одной секунды

	public Map<String, Integer> getList() {
		return list;
	}

	public void setList(Map<String, Integer> list) {
		this.list = list;
	}
	
}

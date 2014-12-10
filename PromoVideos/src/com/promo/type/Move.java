package com.promo.type;

import java.util.Date;

public class Move {
	private String name;
	private Integer audience;
	private Integer price;
	private Date duration;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAudience() {
		return audience;
	}
	public void setAudience(Integer audience) {
		this.audience = audience;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getDuration() {
		return duration;
	}
	public void setDuration(Date duration) {
		this.duration = duration;
	}
}

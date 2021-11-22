package com.restassured.tests;

import java.util.List;

public class api<coursetitle> {
	
	private List<coursetitle> coursetitle;
	private String price;
	
	
	public List<coursetitle> getCoursetitle() {
		return coursetitle;
	}
	public void setCoursetitle(List<coursetitle> coursetitle) {
		this.coursetitle = coursetitle;
	}
     public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}

package com.app.model;

public class Cart {
	private int productId;
	private int customerId;
	private String productName;
	private int price;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Cart(int productId, int customerId, String productName, int price) {
		super();
		this.productId = productId;
		this.customerId = customerId;
		this.productName = productName;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Cart [productName=" + productName
				+ ", price=" + price + "]";
	}

	

}

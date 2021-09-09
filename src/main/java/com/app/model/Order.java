package com.app.model;

public class Order {
	private int orderId;
	private int productId;
	private String productName;
	private double price;
	private String orderStatus;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Order(int orderId, int productId, String productName, double price, String orderStatus) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.orderStatus = orderStatus;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId +
				 ", productName=" + productName + ", price=" + price + ", orderStatus=" + orderStatus + "]";
	}

	
	

}

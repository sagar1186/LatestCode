package com.boat.hackathon.model;

public class Request {
	private String item_upc;
	private String bt_customer_id;
	private String  payment_method_token;
	public String getPayment_method_token() {
		return payment_method_token;
	}
	public void setPayment_method_token(String payment_method_token) {
		this.payment_method_token = payment_method_token;
	}
	public void setItem_upc(String item_upc) {
		this.item_upc = item_upc;
	}
	
	public String getItem_upc() {
		return item_upc;
	}
	public String getBt_customer_id() {
		return bt_customer_id;
	}
	public void setBt_customer_id(String bt_customer_id) {
		this.bt_customer_id = bt_customer_id;
	}
	

}

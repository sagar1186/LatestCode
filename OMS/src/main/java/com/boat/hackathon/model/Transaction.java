package com.boat.hackathon.model;

public class Transaction {
	
	
	private String bt_customer_id;
	private String payment_method_token;
	private String sub_merchant_id;
	private String amount;
	private String service_fee;
	
	public String getBt_customer_id() {
		return bt_customer_id;
	}
	public void setBt_customer_id(String bt_customer_id) {
		this.bt_customer_id = bt_customer_id;
	}
	public String getPayment_method_token() {
		return payment_method_token;
	}
	public void setPayment_method_token(String payment_method_token) {
		this.payment_method_token = payment_method_token;
	}
	public String getSub_merchant_id() {
		return sub_merchant_id;
	}
	public void setSub_merchant_id(String sub_merchant_id) {
		this.sub_merchant_id = sub_merchant_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getService_fee() {
		return service_fee;
	}
	public void setService_fee(String service_fee) {
		this.service_fee = service_fee;
	}

	 	
	

}

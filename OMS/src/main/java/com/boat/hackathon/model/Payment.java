package com.boat.hackathon.model;

public class Payment {
	private String bt_customer_id;
	private String payment_method_token;
	private String sub_merchant_id;
	private String amount;
	private String service_fee;
	private String payment_id;
	private String response_description;
	private String response_authorization_code;
	private String settlement_batch_id;
	
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getResponse_description() {
		return response_description;
	}
	public void setResponse_description(String response_description) {
		this.response_description = response_description;
	}
	public String getResponse_authorization_code() {
		return response_authorization_code;
	}
	public void setResponse_authorization_code(String response_authorization_code) {
		this.response_authorization_code = response_authorization_code;
	}
	public String getSettlement_batch_id() {
		return settlement_batch_id;
	}
	public void setSettlement_batch_id(String settlement_batch_id) {
		this.settlement_batch_id = settlement_batch_id;
	}
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

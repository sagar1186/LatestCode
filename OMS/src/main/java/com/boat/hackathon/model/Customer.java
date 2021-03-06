package com.boat.hackathon.model;

import java.util.ArrayList;

public class Customer {

	private String lastName;

    private String phone;

    private String fax;

    private String website;

    private String email;

    private String company;

    private String paymentMethodNonce;

   // private Address[] addresses;
    private ArrayList<Address> address =new ArrayList<Address>();

    public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPaymentMethodNonce() {
		return paymentMethodNonce;
	}

	public void setPaymentMethodNonce(String paymentMethodNonce) {
		this.paymentMethodNonce = paymentMethodNonce;
	}

	

	

	public ArrayList<Address> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	private String itemId;

    private String firstName;

    private String macId;
	
	
}

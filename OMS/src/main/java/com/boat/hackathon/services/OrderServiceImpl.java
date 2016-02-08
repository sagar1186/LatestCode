package com.boat.hackathon.services;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.boat.hackathon.model.Catalog;
import com.boat.hackathon.model.CatalogList;
import com.boat.hackathon.model.Customer;
import com.boat.hackathon.model.Item;
import com.boat.hackathon.model.Order;
import com.boat.hackathon.model.Payment;
import com.boat.hackathon.model.PaymentDetail;
import com.boat.hackathon.model.Request;
import com.boat.hackathon.model.Response;
import com.boat.hackthon.constant.OrderURIConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class OrderServiceImpl implements OrderService {	
	
	
	Response response=new Response();
	RestTemplate restTemplate = new RestTemplate();
	Order order = new Order();
	PaymentDetail paymentDetail= new PaymentDetail();
	
	
	class CheaperItemPriceCompTest implements Comparator<Item>{
		 
	    @Override
	    public int compare(Item e1, Item e2) {
	        if(e1.getAmount() > e2.getAmount()){
	            return 1;
	        } else {
	            return -1;
	        }
	    }
	}
	
	
	
	
	class CheapestItemPriceComp implements Comparator<Catalog>{
		 
	    @Override
	    public int compare(Catalog e1, Catalog e2) {
	        if(Double.parseDouble(e1.getPrice()) > Double.parseDouble(e2.getPrice())){
	            return 1;
	        } else {
	            return -1;
	        }
	    }
	}
	
	
	
	public Response createOrder(Request boatReq)
	{   /*
		String address = restTemplate.getForObject(OrderURIConstants.CUSTOMER_URL+21655562,String.class);
		System.out.println("address--->"+address);
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		Customer customer = gson.fromJson(address, Customer.class); 
		System.out.println("********-"+customer.getAddress().get(0).getPostalCode());
		*/
		
		
	System.out.println("boatReq--->"+boatReq.getItem_upc()+"--"+boatReq.getBt_customer_id()); 
	System.out.println("In service layer****");
	int id=Calendar.getInstance().get(Calendar.MILLISECOND);
	String orderId=Integer.toString(id);
	Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
    String strDate = sdf.format(date);
		
	//Call catalog webservice for item
	System.out.println("*****Calling get Catalog web service*********");	
	String upc=boatReq.getItem_upc();
	//CatalogList catalogs = restTemplate.getForObject(OrderURIConstants.CATALOG_URL+upc,CatalogList.class);
	
	List<LinkedHashMap> catalogs = restTemplate.getForObject(OrderURIConstants.CATALOG_URL+upc,List.class);
	ArrayList<Catalog> items=new ArrayList<Catalog>();
	for(LinkedHashMap map : catalogs){
		Catalog cat=new Catalog();
		cat.setAvailability(Boolean.getBoolean(map.get("availability").toString()));
		cat.setPrice((map.get("price").toString()));
		cat.setItem_upc((map.get("item_upc").toString()));
		cat.setSub_merchant_id((map.get("sub_merchant_id").toString()));
		cat.setQuantity((map.get("quantity").toString()));		
		items.add(cat);
		
	
	}
	
	//delete it
	
	/*Catalog item1=new Catalog();
	item1.setItem_upc("16234");
	item1.setSub_merchant_id("Store_3");
	item1.setPrice(15.00);
	item1.setQuantity(1);
	item1.setAvailability(true);
	
	Catalog item2=new Catalog();
	item2.setItem_upc("458636");
	item2.setSub_merchant_id("Store_3");;
	item2.setPrice(18.00);
	item2.setQuantity(1);
	item2.setAvailability(true);
	
	
	ArrayList<Catalog> items1  =new ArrayList<Catalog>();
	 items1.add(item1);
	 items1.add(item2);*/
	//delete it
		
	
	Collections.sort(items,new CheapestItemPriceComp());	
	Catalog cheapestItem =new Catalog();
	
	if(null!=items)
	{
		cheapestItem=items.get(0);
		System.out.println("******Cheapest Price-->"+cheapestItem.getPrice());
	}
	else{
		response.setStatus("Failed at Order");
		//response.setTimestamp(strDate);
		return response;
	}
	
	double price=Double.parseDouble(cheapestItem.getPrice());
	double amount=price;
	double serviceFee=price*0.1;
	serviceFee = Math.round(serviceFee * 100);
	serviceFee = serviceFee/100;
	
	System.out.println("Price--ServiceFee--Amount-->"+price+"--"+serviceFee+"--"+amount);
	//Calling order data service-Save Order data into DB
	
	System.out.println("orderId--->"+orderId);
	//Calling DataService
	System.out.println("*****Calling Order data web service*********");
	order.setAmount(Double.toString(amount));
	order.setCustomer_id(boatReq.getBt_customer_id());
	order.setItem_upc(cheapestItem.getItem_upc());
	order.setMerchant_id(cheapestItem.getSub_merchant_id());
	order.setSession_id("54hgjh");
	order.setStatus("complete");
	order.setTransaction_id(orderId);
	order.setTransaction_type("order");
	System.out.println("*****Calling Order data web service*********");
	restTemplate.postForObject(OrderURIConstants.ORDER_DATASERVICE_URL,order, Order.class);


	
	//End
	
	//call payment web service
	System.out.println("*****In  Payment web service*********");
	Payment paymentReq=new Payment();
	paymentReq.setAmount(Double.toString(amount));
	paymentReq.setBt_customer_id(boatReq.getBt_customer_id());
	paymentReq.setPayment_method_token(boatReq.getPayment_method_token());
	paymentReq.setService_fee(Double.toString(serviceFee));
	paymentReq.setSub_merchant_id(cheapestItem.getSub_merchant_id());
	System.out.println("*****Calling get payment web service*********");
	Payment paymentResponse=restTemplate.postForObject(OrderURIConstants.PAYMENT_URL, paymentReq, Payment.class);
	if(null==paymentResponse.getPayment_id())
	{
		paymentDetail.setStatus("failed");
		response.setStatus("Failed at Payment");
	}
	else{
		paymentDetail.setStatus("complete");
		response.setStatus("success");
	}
	//end
	 
	  	
	//Calling payment data service-Save payment data into DB
	System.out.println("*****In Payment data web service*********");
	paymentDetail.setAmount(amount);
	paymentDetail.setCustomer_id(boatReq.getBt_customer_id());
	paymentDetail.setItem_upc(cheapestItem.getItem_upc());
	paymentDetail.setMerchant_id(cheapestItem.getSub_merchant_id());
	paymentDetail.setSession_id("54hgjh");	
	paymentDetail.setTransaction_id(orderId);
	paymentDetail.setTransaction_type("payment");	
	System.out.println("*****Calling Payment data web service*********");
	restTemplate.postForObject(OrderURIConstants.PAYMENT_DATASERVICE_URL, paymentDetail, PaymentDetail.class);

	//End
	
	
	
	/* ******
	 * Order order=new Order();
	// order.getPayment("PayPal");
	 
		Item item1=new Item();
		item1.setItem_upc(12345);
		item1.setMerchant_id(11);;
		item1.setUnitPrice(11.2);
		item1.setQuantity(1);
		item1.setAvailability("true");
		
		Item item2=new Item();
		item2.setItem_upc(45636);
		item2.setMerchant_id(67);;
		item2.setUnitPrice(10.20);
		item2.setQuantity(1);
		item2.setAvailability("true");
		
		Item item3=new Item();
		item3.setItem_upc(23645);
		item3.setMerchant_id(234);;
		item3.setUnitPrice(11.55);
		item3.setQuantity(1);
		item3.setAvailability("true");
		
		 ArrayList<Item> items1  =new ArrayList<Item>();
		 items1.add(item1);
		 items1.add(item2);
		 items1.add(item3);
		
		//ArrayList<Item> items =order.getItemList().getItem();

			Collections.sort(items1,new CheaperItemPriceCompTest());
			Item cheaperItem1=items1.get(0);
			//Catalog cheaperItem =new Catalog();
		
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
	    String strDate = sdf.format(date);
		int orderId=Calendar.getInstance().get(Calendar.MILLISECOND);
		
		
		System.out.println("**********cheaperItem-->"+cheaperItem1.getUnitPrice());
		
		*******/
		
	//String address = restTemplate.getForObject(OrderURIConstants.CUSTOMER_URL+boatReq.getBt_customer_id(),String.class);
		//System.out.println("address--->"+address);
		
		response.setOrderId(orderId);
		//response.setStatus("Success");
		response.setTimestamp(strDate);
		//response.setPayment(order.getPayment());	
		
		
		return response;
		
	
		
	}


}

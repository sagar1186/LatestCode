package com.boat.hackathon.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boat.hackathon.model.Request;
import com.boat.hackathon.model.Response;
import com.boat.hackathon.services.OrderService;
import com.boat.hackathon.services.OrderServiceImpl;
import com.boat.hackthon.constant.OrderURIConstants;

/**
 * Handles requests for the application home page.
 */
@Controller
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	OrderService orderService=new OrderServiceImpl();
	@RequestMapping(value = OrderURIConstants.CREATE_ORDER, method = RequestMethod.POST)
	public @ResponseBody Response createOrder(@RequestBody Request  request) {
		System.out.println("Start createOrder.");
		Response response =orderService.createOrder(request);
		//order.setTimestamp(new Date());
		//empData.put(order.getId(), emp);
		return response;
	}
	
	
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}*/
	
}

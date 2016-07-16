package com.congun.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.User;
import com.congun.web.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	

	@RequestMapping(value="/" , method=RequestMethod.GET , produces= MediaType.ALL_VALUE)
	public String welcomeMethod(){
		return "This is the welcome message";
	}
	
	public ResponseEntity<Void> login(@RequestBody User user){
		System.out.println("Creating user :: "+user.getUsername());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public 	String registration(@RequestBody User user){
		System.out.println(user.getUsername());
		return userService.saveUser(user);
		
		
	}
	
	@RequestMapping(value="/updateuser" , method=RequestMethod.PUT)
	public 	String updateUser(@RequestBody User user){
		System.out.println(user.getUsername());
		return userService.updateUser(user);
		
		
	}
		
	@RequestMapping(value="/getuser/{username}/" , method=RequestMethod.GET)
	public 	String getUser(@PathVariable("username") String username){
		System.out.println("Getting details from Controller : "+username);
		return userService.getUserDetails(username);
		
	}
	
	
	
	
	
	/*@RequestMapping(value="/updatequote/{Id}" , method=RequestMethod.GET)
	public 	String updateQuotation(@RequestBody SupplierQuote supplierQuotation,@PathVariable("Id") int Id){
		System.out.println("Posting Requirement details from Supplier");
		return supplierService.updateQuote(supplierQuotation,Id);
		
	}*/
	
	
	
	
	
}

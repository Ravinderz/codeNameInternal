package com.congun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String login(@RequestBody User user){
		System.out.println("Check login of User :: "+user.getUsername());
		return userService.authLogin(user);
	}
	
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public String registration(@RequestBody User user){
		System.out.println(user.getUsername());
		return userService.saveUser(user);		
	}
	
	@RequestMapping(value="/updateuser" , method=RequestMethod.PUT)
	public 	String updateUser(@RequestBody User user){
		System.out.println("Starting Update User :"+user.getUsername());
		return userService.updateUser(user);
	}
		
	@RequestMapping(value="/getuser/{username}/" , method=RequestMethod.GET)
	public 	String getUser(@PathVariable("username") String username){
		System.out.println("Getting details from Controller : "+username);
		return userService.getUserDetails(username);	
	}
	
}

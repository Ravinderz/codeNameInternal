package com.congun.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.User;
import com.congun.web.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/" , method=RequestMethod.GET , produces= MediaType.ALL_VALUE)
	public String welcomeMethod(){
		return "This is the welcome message";
	}
	
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String login(@RequestBody User user){
		logger.info("Entered into UserController.login method  User:"+user);
		return userService.authLogin(user);
	}
	
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public String registration(@RequestBody User user){
		logger.info("Entered into UserController.registration method  User:"+user);
		return userService.saveUser(user);		
	}
	
	@RequestMapping(value="/updateuser" , method=RequestMethod.PUT)
	public 	String updateUser(@RequestBody User user){
		logger.info("Entered into UserController.updateUser method  User:"+user);
		return userService.updateUser(user);
	}
		
	@RequestMapping(value="/getuser/{username}" , method=RequestMethod.GET)
	public 	String getUser(@PathVariable("username") String username){
		logger.info("Entered into UserController.getUser method  Username:"+username);
		return userService.getUserDetails(username);	
	}
	
	@RequestMapping(value="/getuserById/{id}" , method=RequestMethod.GET)
	public 	String getUserById(@PathVariable("id") long id){
		logger.info("Entered into UserController.getUserById method  UserId:"+id);
		return userService.getUserById(id);	
	}
	
	@RequestMapping(value="/updatePassword/{userId}" , method=RequestMethod.PUT)
	public 	String updatePassword(@PathVariable("userId") long userId,@RequestParam String oldPassword,@RequestParam String newPassword){
		logger.info("Entered into UserController.updatePassword method  UserId:"+userId);
		return userService.updatePassword(userId,oldPassword,newPassword);	
	}
	
	
}


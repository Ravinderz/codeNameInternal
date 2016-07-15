package com.congun.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.UserDAO;
import com.congun.web.model.User;


@Service
@Transactional
public class UserServiceImpl {
   
	@Autowired
	UserDAO userdao;
	
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		String status= userdao.saveUser(user);
		if(status.equals("US01"))
			return "User "+user.getUsername()+" has been successfully created!!!";
		else if(status.equals("UE01")) {
			return "User "+user.getUsername()+" Already Exists!!!";
		} else
		return "Error while creating user "+user.getUsername();
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean authenticateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserDetails(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

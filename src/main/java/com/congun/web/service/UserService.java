package com.congun.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.UserDao;
import com.congun.web.model.User;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;


@Service
@Transactional
public class UserService {
   
	@Autowired
	UserDao userdao;
	
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		String status= userdao.saveUser(user);
		if(status.equals(ResponseConstants.SUCCESS_CODE))
			return "User "+user.getUsername()+" has been successfully created!!!";
		else if(status.equals(ResponseConstants.FAILURE_CODE)) {
			return "User "+user.getUsername()+" Already Exists!!!";
		} else
		return "Error while creating user "+user.getUsername();
		
	}
	
	public String updateUser(User user) {
		// TODO Auto-generated method stub
		String status= userdao.updateUser(user);
		if(status.equals(ResponseConstants.SUCCESS_CODE))
			return "User "+user.getUsername()+" details has been successfully updated!!!";
		else
		return "Error while creating user "+user.getUsername();
		
	}
	

	
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean authenticateUser(User user) {
		return false;
	}

	
	public String getUserDetails(String username) {
		System.out.println("Getting details fromr ServiceImpl : "+username);
		
		return ApplicationUtil.getJsonResponse(userdao.getUserDetails(username));
	}
	
	

}

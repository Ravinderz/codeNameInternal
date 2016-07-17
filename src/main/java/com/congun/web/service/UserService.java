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
			return ApplicationUtil.getJsonResponse(user);
		else
			return status;
		
	}
	
	public String authLogin(User user){
		user = userdao.authenticateUser(user);
		if(user != null){
			return ApplicationUtil.getJsonResponse(user);
		}else
			return ResponseConstants.FAILURE_CODE;
	}
	
	public String updateUser(User user) {
		// TODO Auto-generated method stub
		String status= userdao.updateUser(user);
		return status;				
	}
	

	
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean authenticateUser(User user) {
		return false;
	}

	
	public String getUserDetails(String username) {
		System.out.println("Getting details fromr ServiceImpl : "+username);
		User user = userdao.getUserDetails(username);
		if(user != null){
			return ApplicationUtil.getJsonResponse(user);	
		}else
			return ResponseConstants.FAILURE_CODE;
		
		
	}
	
	

}

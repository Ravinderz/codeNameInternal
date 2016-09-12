package com.congun.web.service;

import org.apache.log4j.Logger;
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
	private static Logger logger = Logger.getLogger(UserService.class);
	@Autowired
	UserDao userdao;
	
	public String saveUser(User user) {
		logger.info("Entered into SupplierQuoteService.UserService method ");
		String status= userdao.saveUser(user);
		if(status.equals(ResponseConstants.USER_SUCCESS_CODE))
			return ApplicationUtil.getJsonResponse(user);
		else
			return status;
		
	}
	
	public String authLogin(User user){
		logger.info("Entered into SupplierQuoteService.authLogin method");
		user = userdao.authenticateUser(user);
		if(user != null){
			return ApplicationUtil.getJsonResponse(user);
		}else
			return ResponseConstants.USER_FAILURE_CODE;
	}
	
	public String updateUser(User user) {
		logger.info("Entered into SupplierQuoteService.updateUser method ");
		String status= userdao.updateUser(user);
		return status;				
	}
	

	
	public void deleteUser(User user) {
		logger.info("Entered into SupplierQuoteService.deleteUser method ");
		
	}

	
	public boolean authenticateUser(User user) {
		logger.info("Entered into SupplierQuoteService.authenticateUser method ");
		return false;
	}

	
	public String getUserDetails(String username) {
		logger.info("Entered into SupplierQuoteService.getUserDetails method Username:"+username);
		User user = userdao.getUserDetails(username);
		if(user != null){
			return ApplicationUtil.getJsonResponse(user);	
		}else
			return ResponseConstants.USER_FAILURE_CODE;
		
		
	}
	
	

}

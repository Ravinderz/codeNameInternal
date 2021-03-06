package com.congun.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.UserDao;
import com.congun.web.model.User;
import com.congun.web.model.UserQuery;
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
	
	
	public String getUserById(long id) {
		logger.info("Entered into SupplierQuoteService.getUserById method UserID:"+id);
		User user = userdao.getUserById(id);
		if(user != null){
			return ApplicationUtil.getJsonResponse(user);	
		}else
			return ResponseConstants.USER_FAILURE_CODE;
		
		
	}

	public String updatePassword(long userId, String oldPassword, String newPassword) {
		logger.info("Entered into SupplierQuoteService.updatePassword method ");
		String status= userdao.updateUser(userId,oldPassword,newPassword);
		return status;	
	}

	public String forgotPassword(String email,String hash,User user) {
		logger.info("Entered into SupplierQuoteService.forgetPassword method ");
		String status= userdao.forgotPassword(email,hash,user);
		return status;	
	}
	
	public String sendEmail(String email) {
		logger.info("Entered into SupplierQuoteService.forgetPassword method ");
		String status= userdao.sendEmail(email);
		return status;	
	}
	
	public String postQuery(final UserQuery usrQry) {
		logger.info("Entered into UserService.postQuery method ");
		String status= userdao.postQuery(usrQry);
		if(status.equals(ResponseConstants.CONTACT_US_SUCCESS))
		{
			new Thread(new Runnable() {
	    	    public void run() {
	    	    	try {
	    	        	ApplicationUtil.emailQueries(usrQry);
	    	        	
	    	        } catch (Exception ex) {
	    	        	logger.error("Exception occured."+ex.getMessage());
	    	        }
	    	    }
	    	}).start();    
			
		}
		return status;	
	}
}


package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.User;
import com.congun.web.util.GenerateHash;
import com.congun.web.util.ResponseConstants;

@Repository
public class UserDao {
	private static Logger logger = Logger.getLogger(UserDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public String saveUser(User user){
		logger.info("Entered into UserDao.saveUser method ");
		try{
		List<User> userList = getSession().createCriteria(User.class).add(Restrictions.eq("username", user.getUsername())).list()	;
		if(userList.size() == 0){
		user.setPassword(GenerateHash.getHash(user.getPassword()));

		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		user.setCreatedtime(currTime);
		user.setUpdatedtime(currTime);
		user.setActiveFlag(1);
		getSession().saveOrUpdate(user);
			return ResponseConstants.USER_SUCCESS_CODE;
		}else
			return ResponseConstants.USER_FAILURE_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return ResponseConstants.USER_EXCEPTION_CODE;
		}
	}
	
	@Transactional
	public String updateUser(User user){
		logger.info("Entered into UserDao.updateUser method ");
		try{
		//User existingUser = (User)getSession().createCriteria(User.class).add(Restrictions.eq("userId", user.getUserId())).list().get(0);
		user.setPassword(GenerateHash.getHash(user.getPassword()));
		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		//user.setCreatedtime(existingUser.getCreatedtime());
		user.setUpdatedtime(currTime);
		getSession().saveOrUpdate(user);
			return ResponseConstants.USER_SUCCESS_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return ResponseConstants.USER_EXCEPTION_CODE;
		}
	}
	
	@SuppressWarnings("unchecked")
	public User authenticateUser(User user){
		logger.info("Entered into UserDao.authenticateUser method ");
		user.setPassword(GenerateHash.getHash(user.getPassword()));
		List<User> userList = getSession().createQuery("from User where username = :username and password = :password").setParameter("username", user.getUsername()).setParameter("password",user.getPassword()).list();
		//user = (User)getSession().createCriteria(User.class).add(Restrictions.eq("user.username", username)).add(Restrictions.eq("user.password", password)).uniqueResult();
		if(userList.size() > 0){//getting null pointer exception
			user = userList.get(0);
			return user;
		}
		return null;
	}
	

	@Transactional
	public User getUserDetails(String username){
		logger.info("Entered into UserDao.getUserDetails method username: "+username);
		try{
			Criteria criteria = getSession().createCriteria(User.class);
			criteria.setMaxResults(1);
		User user= (User) criteria.add(Restrictions.eq("username",username)).uniqueResult();
		return user;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public User getUserById(long id){
		logger.info("Entered into UserDao.getUserbyId method id: "+id);
		try{
			Criteria criteria = getSession().createCriteria(User.class);
			criteria.setMaxResults(1);
		User user= (User) criteria.add(Restrictions.eq("userId",id)).uniqueResult();
		return user;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
    @Transactional
	public String updateUser(long userId,String oldPassword, String newPassword) {
		logger.info("Entered into UserDao.updateUser method ");
		try{
			 String oldPasswordhash = GenerateHash.getHash(oldPassword);
			User userDetails = getUserById(userId);
			String oldPasswordFromDB = userDetails.getPassword();
			if(oldPasswordFromDB.equals(oldPasswordhash)){
			userDetails.setPassword(GenerateHash.getHash(newPassword));
		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		userDetails.setUpdatedtime(currTime);
		getSession().saveOrUpdate(userDetails);
			return ResponseConstants.USER_SUCCESS_CODE;
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseConstants.USER_EXCEPTION_CODE;
		}
		return ResponseConstants.WRONG_PASSWORD_CODE;
		
	
    }

}

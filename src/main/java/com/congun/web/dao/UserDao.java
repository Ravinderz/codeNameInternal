package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public String saveUser(User user){
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
			return ResponseConstants.SUCCESS_CODE;
		}else
			return ResponseConstants.FAILURE_CODE;
		}catch(Exception e){
			return ResponseConstants.EXCEPTION_CODE;
		}
	}
	
	@Transactional
	public String updateUser(User user){
		try{
		user.setPassword(GenerateHash.getHash(user.getPassword()));
		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		user.setUpdatedtime(currTime);
		getSession().saveOrUpdate(user);
			return ResponseConstants.SUCCESS_CODE;
		}catch(Exception e){
			return ResponseConstants.EXCEPTION_CODE;
		}
	}
	
	@SuppressWarnings("unchecked")
	public User authenticateUser(User user){
		user.setPassword(GenerateHash.getHash(user.getPassword()));
		List<User> userList = getSession().createQuery("from User where username = :username and password = :password").setParameter("username", user.getUsername()).setParameter("password",user.getPassword()).list();
		//user = (User)getSession().createCriteria(User.class).add(Restrictions.eq("user.username", username)).add(Restrictions.eq("user.password", password)).uniqueResult();
		if(userList.size() > 0){//getting null pointer exception
			user = userList.get(0);
			System.out.println("User Exists!!");
			System.out.println("username :: "+user.getUsername());
			System.out.println("password :: "+user.getPassword());
			System.out.println("userID :: "+user.getUserId());
			return user;
		}
		return null;
	}
	

	public User getUserDetails(String username){
		try{
			System.out.println("Getting details from DAOImpl :"+username+":");
			Criteria criteria = getSession().createCriteria(User.class);
			criteria.setMaxResults(1);
		User user= (User) criteria.add(Restrictions.eq("username",username)).uniqueResult();
		System.out.println("Got user with "+user.getUsername()+" from database");
		return user;
		}catch(Exception e){
			System.out.println("Entered Exception Block : ");
			e.printStackTrace();
			return null;
		}
	}

}

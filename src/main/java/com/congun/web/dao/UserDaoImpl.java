package com.congun.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.User;
import com.congun.web.util.GenerateHash;

@Repository
public class UserDaoImpl {
	
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
		getSession().saveOrUpdate(user);
			return "US01";
		}else
			return "UE01";
		}catch(Exception e){
			return "UE02";
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean authenticateUser(User user){
		user.setPassword(GenerateHash.getHash(user.getPassword()));
		List<User> userList = getSession().createQuery("from User where username = :username and password = :password").setParameter("username", user.getUsername()).setParameter("password",user.getPassword()).list();
		//user = (User)getSession().createCriteria(User.class).add(Restrictions.eq("user.username", username)).add(Restrictions.eq("user.password", password)).uniqueResult();
		if(userList.size() > 0){//getting null pointer exception
			user = userList.get(0);
			System.out.println("username :: "+user.getUsername());
			System.out.println("password :: "+user.getPassword());
			System.out.println("userID :: "+user.getUserId());
			return true;
		}
		return false;
	}
	

}

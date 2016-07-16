package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.SupplierQuote;
import com.congun.web.util.ResponseConstants;

@Repository
public class SupplierQuoteDao {

	
@Autowired
private SessionFactory sessionFactory;

@Transactional
protected Session getSession(){
	return sessionFactory.getCurrentSession();
}

@Transactional
@SuppressWarnings("unchecked")
public String saveQuote(SupplierQuote supplierQuote){
	try{
		
		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		supplierQuote.setCreatedTime(currTime);
		supplierQuote.setUpdatedTime(currTime);
		getSession().saveOrUpdate(supplierQuote);
		
		return ResponseConstants.SUCCESS_CODE;
	}catch(Exception e){
		
		return ResponseConstants.EXCEPTION_CODE;
	}
}

@Transactional
@SuppressWarnings("unchecked")
public String updateQuote(SupplierQuote supplierQuote,int Id)
{
	
	try{
		if(getQuoteDetails(Id) != null){
		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		
		return ResponseConstants.SUCCESS_CODE;
		}else
			return ResponseConstants.FAILURE_CODE;
		}catch(Exception e){
			return ResponseConstants.EXCEPTION_CODE;
	}	
}

@Transactional
@SuppressWarnings("unchecked")
public SupplierQuote getQuoteDetails(int Id)
{
	
	try{
		SupplierQuote supplierQuotation = (SupplierQuote)getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("quoteId", Id)).list().get(0);		
		return supplierQuotation;
		}catch(Exception e){
			return null;
	}
}


	
}

package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public String saveQuote(SupplierQuote supplierQuote){
	try{
		
		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		supplierQuote.setCreatedTime(currTime);
		supplierQuote.setUpdatedTime(currTime);
		supplierQuote.setActiveFlag(0);
		getSession().saveOrUpdate(supplierQuote);
		
		return ResponseConstants.SUCCESS_CODE;
	}catch(Exception e){
		e.printStackTrace();
			return ResponseConstants.EXCEPTION_CODE;
	}
}

@Transactional
public String updateQuote(SupplierQuote supplierQuote)
{
	try{
		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		supplierQuote.setUpdatedTime(currTime);
		getSession().saveOrUpdate(supplierQuote);
		
		return ResponseConstants.SUCCESS_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return ResponseConstants.EXCEPTION_CODE;
	}	
}

@Transactional
@SuppressWarnings("unchecked")
public List<SupplierQuote> getQuotesbySupplier(long supplierId)
{
	System.out.println("Getting Quotes by Supplier Id from DB : "+supplierId);
	try{
		List<SupplierQuote>  supplierQuotationList = (ArrayList<SupplierQuote>) getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("quotePostedById", supplierId)).list();
		System.out.println("No of Quotes Returned : "+supplierQuotationList.size());
		return supplierQuotationList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
	}
}

@Transactional
@SuppressWarnings("unchecked")
public List<SupplierQuote> getQuotesbyRequirement(long requirementId)
{
	try{
		List<SupplierQuote>  supplierQuotationList = (ArrayList<SupplierQuote>) getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("submittedforReq", requirementId)).list();		
		return supplierQuotationList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
	}
}

@Transactional
@SuppressWarnings("unchecked")
public int getNoOfQuotesbyRequirement(long requirementId)
{
	try{
		List<SupplierQuote>  supplierQuotationList = (ArrayList<SupplierQuote>) getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("submittedforReq", requirementId)).list();		
		return supplierQuotationList.size();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
	}
}

@Transactional
public SupplierQuote getQuotesbyId(long Id)
{
	System.out.println("Getting Quotation from DB :" + Id);
	try{
		SupplierQuote supplierQuotation = (SupplierQuote)getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("quoteId",Id)).list().get(0);
		
		if(supplierQuotation != null){
			System.out.println("Got a record");
		}else
			System.out.println("Supplier quote is null");
		return supplierQuotation;
		}catch(Exception e){
			e.printStackTrace();
			return null;
	}
}


}

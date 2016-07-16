package com.congun.web.dao;

import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.congun.web.model.ContractorRequirement;

@Repository
public class RequirementQuoteDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	/*To store contractor requirements into contractorrequirement table*/
	public void saveRequirement(ContractorRequirement requirment) {
		java.util.Date date= new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		requirment.setCreatedTime(time);
		requirment.setUpdatedTime(time);
		sessionFactory.getCurrentSession().saveOrUpdate(requirment);
		
	}

	/*To get requirement details by requirement Id  */
	public ContractorRequirement getRequirementById(long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractorRequirement.class);
		criteria.add(Restrictions.eq("requirmentId", id));
		ContractorRequirement reqObject = (ContractorRequirement) criteria.uniqueResult();
		return reqObject;
		
	}
    
	/*To update contractor requirements into contractorrequirement table*/
	public void updateRequirement(ContractorRequirement requirment) {
		java.util.Date date= new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		requirment.setUpdatedTime(time);
		sessionFactory.getCurrentSession().saveOrUpdate(requirment);
	}
	
	
	

}

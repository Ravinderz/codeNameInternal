package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.List;

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
	public void saveRequirement(ContractorRequirement requirement) {
		java.util.Date date= new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		requirement.setActiveFlag(1);
		requirement.setCreatedTime(time);
		requirement.setUpdatedTime(time);
		sessionFactory.getCurrentSession().saveOrUpdate(requirement);
		
	}

	/*To get requirement details by requirement Id  */
	public ContractorRequirement getRequirementById(long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractorRequirement.class);
		criteria.add(Restrictions.eq("requirementId", id));
		ContractorRequirement reqObject = (ContractorRequirement) criteria.uniqueResult();
		return reqObject;
		
	}
    
	/*To update contractor requirements into contractorrequirement table*/
	public void updateRequirement(ContractorRequirement requirement) {
		java.util.Date date= new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		requirement.setUpdatedTime(time);
		sessionFactory.getCurrentSession().saveOrUpdate(requirement);
	}
	
	/*To get the list of requirments by contractorid */
	public List<ContractorRequirement> getAllRequirementsByConctractorId(long id){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractorRequirement.class);
		criteria.add(Restrictions.eq("contractorId", id)).add( Restrictions.eq("activeFlag", 1));
		List<ContractorRequirement> reqList = criteria.list();	
		return reqList;
	}
	

}

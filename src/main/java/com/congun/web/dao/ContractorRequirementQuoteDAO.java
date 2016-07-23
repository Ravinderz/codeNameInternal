package com.congun.web.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.AddEquipment;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;
import com.congun.web.util.SupplierMapperComponent;

@Repository
public class ContractorRequirementQuoteDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	SupplierMapperComponent mapperComponent;
	
	@Transactional
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	/*To store contractor requirements into contractorrequirement table*/
	@SuppressWarnings("deprecation")
	public String saveRequirement(ContractorRequirement requirement) {
		try {
		java.util.Date date= new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		requirement.setActiveFlag(1);
		requirement.setCreatedTime(time);
		requirement.setUpdatedTime(time);
		requirement.setStartDate(ApplicationUtil.formatDate(requirement.getStartDate()));
		sessionFactory.getCurrentSession().saveOrUpdate(requirement);
		
		if (mapperComponent.SupplierMapperPreprocessor(requirement)) {
			System.out
					.println("Mapping the Suppliers for submitted Requirement :"
							+ requirement.getRequirementId());
		} else {
			System.out.println("Could Not start Mapping for requirement: "
					+ requirement.getRequirementId());
		}
		
		return ResponseConstants.CONTRACTOR_SUCCESS_CODE;
		} catch (ParseException e) {
			e.printStackTrace();
			return ResponseConstants.CONTRACTOR_EXCEPTION_CODE;
		}
	}

	/*To get requirement details by requirement Id  */
	public ContractorRequirement getRequirementById(long id) {
		try{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractorRequirement.class);
		criteria.add(Restrictions.eq("requirementId", id));
		ContractorRequirement reqObject = (ContractorRequirement) criteria.uniqueResult();
		return reqObject;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
    
	/*To update contractor requirements into contractorrequirement table*/
	public String updateRequirement(ContractorRequirement requirement) {
		try{
		//ContractorRequirement existingReq = (ContractorRequirement) getSession().createCriteria(ContractorRequirement.class).add(Restrictions.eq("requirementId", requirement.getRequirementId()));
		java.util.Date date= new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		//requirement.setCreatedTime(existingReq.getCreatedTime());
		requirement.setUpdatedTime(time);
		sessionFactory.getCurrentSession().saveOrUpdate(requirement);
		
		if (mapperComponent.SupplierMapperPreprocessor(requirement)) {
			System.out
					.println("Mapping the Suppliers for submitted Requirement :"
							+ requirement.getRequirementId());
		} else {
			System.out.println("Could Not start Mapping for requirement: "
					+ requirement.getRequirementId());
		}
		
		return ResponseConstants.CONTRACTOR_SUCCESS_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return ResponseConstants.CONTRACTOR_EXCEPTION_CODE;
		}
	}
	
	/*To get the list of requirments by contractorid */
	public List<ContractorRequirement> getAllRequirementsByConctractorId(long id){
		try{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractorRequirement.class);
		criteria.add(Restrictions.eq("contractorId", id)).add( Restrictions.eq("activeFlag", 1));
		List<ContractorRequirement> reqList = criteria.list();	
		return reqList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String updateNoOfQuotes(long requirementId){
		try{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractorRequirement.class);
		ContractorRequirement contractorRequirement = (ContractorRequirement)criteria.add(Restrictions.eq("requirementId",requirementId)).add( Restrictions.eq("activeFlag", 1)).list().get(0);
		contractorRequirement.setNoofquotes(contractorRequirement.getNoofquotes()+1);
		sessionFactory.getCurrentSession().saveOrUpdate(contractorRequirement);
		return ResponseConstants.CONTRACTOR_SUCCESS_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return ResponseConstants.CONTRACTOR_EXCEPTION_CODE;
		}
	}

	public List<AddEquipment> getDistinctCategory() {
		try{
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AddEquipment.class);
			criteria.setProjection(Projections.distinct(Projections.property("equipmentCategory")));
			List<AddEquipment> list= criteria.list();
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;	
		}
	}

	public List<AddEquipment> getEquipmentByCategory(String category) {
		try{
			System.out.println("Category :"+category);
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AddEquipment.class);
			criteria.setProjection(Projections.property("equipment"));
			criteria.add(Restrictions.eq("equipmentCategory", category));
			List<AddEquipment> list= criteria.list();
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;	
		}
	}
	

}

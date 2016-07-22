package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.AddEquipment;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.model.MappingObject;
import com.congun.web.model.SupplierQuote;
import com.congun.web.model.User;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;

@Repository
public class SupplierQuoteDao {

	
@Autowired
private SessionFactory sessionFactory;

@Autowired
public ContractorRequirementQuoteDAO requirementDAO;

@Autowired
public MappingObject mappingobject;

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
		supplierQuote.setActiveFlag(1);
		requirementDAO.updateNoOfQuotes(supplierQuote.getRequirementId());
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
		//SupplierQuote existingQuote = (SupplierQuote) getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("quoteId", supplierQuote.getQuoteId())).list().get(0);
		Date date = new Date();
		Timestamp currTime = new Timestamp(date.getTime());
		//supplierQuote.setCreatedTime(existingQuote.getCreatedTime());
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
		List<SupplierQuote>  supplierQuotationList = (ArrayList<SupplierQuote>) getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("requirementId", requirementId)).list();		
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
		List<SupplierQuote>  supplierQuotationList = (ArrayList<SupplierQuote>) getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("requirementId", requirementId)).list();		
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

@Transactional
	public String addEquipment(AddEquipment equipment) {
		try {
			getSession().saveOrUpdate(equipment);
			return ResponseConstants.SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.EXCEPTION_CODE;
		}
		
	}

@Transactional
	public String getEquipmentDetails(long supplierId) {
		System.out.println("Getting Equipment details from DB :" + supplierId);
		try{
			List<AddEquipment> addEquipment = getSession().createCriteria(AddEquipment.class).add(Restrictions.eq("supplierId",supplierId)).list();
			
			if(addEquipment != null){
				System.out.println("Got a record");
			}else
				System.out.println("Supplier quote is null");
			return ApplicationUtil.getJsonResponse(addEquipment);
			}catch(Exception e){
				e.printStackTrace();
				return null;
		}
	}

@Transactional
	public List<AddEquipment> getAllEquipments(Long supplierId) {
		System.out.println("Getting Equipment details from DB :" + supplierId);
		try{
			Criteria criteria = getSession().createCriteria(AddEquipment.class);
			criteria.add(Restrictions.eq("supplierId", supplierId));
			List<AddEquipment> reqList = criteria.list();
			return reqList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
	}
		
	}

@Transactional
	public String deleteEquipmentById(int equipmentId) {
	System.out.println("Deleting Equipment by ID");
	try{
	Criteria criteria = getSession().createCriteria(AddEquipment.class);
	criteria.add(Restrictions.eq("equipmentId", equipmentId));
	AddEquipment equipment = (AddEquipment) criteria.uniqueResult();
	getSession().delete(equipment);
	return ResponseConstants.SUCCESS_CODE;
	}catch(Exception e){
		e.printStackTrace();
		return ResponseConstants.EXCEPTION_CODE;
	}
	
		
	}

@Transactional
	public List<AddEquipment> getEquipmentByCategory(ContractorRequirement requirement){
		System.out.println("Entered Get Equipments by Category");
		try{
			Criteria criteria = getSession().createCriteria(AddEquipment.class);
			
			List<AddEquipment> machinesList = (ArrayList<AddEquipment>) criteria
					.add(Restrictions.eq("equipmentCategory",
							requirement.getEquipmentCategory()))
					.add(Restrictions.eq("equipment",
							requirement.getEquipmentName())).list();
			
			return machinesList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

@Transactional
public List filterSupplierIds(Set mappedSuppliers,long requirementId){
	System.out.println("Entere the filter Mappers method");
	try{
		List existingMappedIds = (ArrayList<Long>)getSession().createQuery("distinct (supplierId) from MappingObject where requirementId = :requirementId").setParameter("requirementId", requirementId).list();
		
		return existingMappedIds;
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

@Transactional
public void updateMappingObjects(Set<Long> mapperSuppliers,long requirementId){
	
	String suppId="";
	try{
	if(mapperSuppliers.size() > 0){
	for(Long Id:mapperSuppliers){
		suppId = suppId+Id.longValue();
		suppId = suppId+",";
		
	}
	System.out.println("List of Mapped Ids for: "+requirementId+" List: "+suppId);
	//MappingObject mappingobject = new MappingObject();
	mappingobject.setRequirementId(requirementId);
	mappingobject.setSupplierList(suppId);
	getSession().saveOrUpdate(mappingobject);
	}else
		System.out.println("Received Empty Mapped Suppliers for Requirement :"+requirementId);
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("Ëxception Occured while updating the Mapped Objects for Requirement Id: "+requirementId );
	}
}

}

package com.congun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.SupplierQuoteDao;
import com.congun.web.model.SupplierQuote;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;

@Service
@Transactional
public class SupplierQuoteService {
	
	@Autowired
	SupplierQuoteDao supplierDao;
	
	
	public String submitQuote(SupplierQuote supplierQuote){
		
		String status = supplierDao.saveQuote(supplierQuote);
		return status;
	}
	
	public String updateQuote(SupplierQuote supplierQuotation){
		String status = supplierDao.updateQuote(supplierQuotation);
		return status;
	}
	
	public String getQuotationsbySupplier(long supplierId){
		List<SupplierQuote> suppQuotesList = supplierDao.getQuotesbySupplier(supplierId);
		if(suppQuotesList != null)
		return ApplicationUtil.getJsonResponse(suppQuotesList);
		else
			return ResponseConstants.FAILURE_CODE;
	}

	public String getQuotationsbyId(long quoteId){
		System.out.println("Entered Service class");
		SupplierQuote supplierQuote =  supplierDao.getQuotesbyId(quoteId);
		if(supplierQuote != null){
			System.out.println("Supplier Quote Received : "+supplierQuote.getQuotePostedByName());	
		return ApplicationUtil.getJsonResponse(supplierQuote);
		}
		else
			return ResponseConstants.FAILURE_CODE;
	}
	
	public String getQuotationsbyRequirement(long requirementId){
		List<SupplierQuote> suppQuotesList = supplierDao.getQuotesbyRequirement(requirementId);
		if(suppQuotesList != null)
		return ApplicationUtil.getJsonResponse(suppQuotesList);
		else
			return ResponseConstants.FAILURE_CODE;
	} 
	
	public int getNoOfQuotationsbyRequirement(long requirementId){
		int count = supplierDao.getNoOfQuotesbyRequirement(requirementId);
		if( count > 0)
		return count;
		else
			return 0;
	}
	
	public String addEquipment(AddEquipment equipment) {
		
		return supplierDao.addEquipment(equipment);
	}

	
		
}

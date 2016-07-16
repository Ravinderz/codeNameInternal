package com.congun.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.SupplierQuoteDao;
import com.congun.web.model.SupplierQuote;
import com.congun.web.util.ResponseConstants;

@Service
@Transactional
public class SupplierQuoteService {
	
	@Autowired
	SupplierQuoteDao supplierDao;
	
	
	public String submitQuote(SupplierQuote supplierQuote){
		
		String status = supplierDao.saveQuote(supplierQuote);
		
		if(status.equals(ResponseConstants.SUCCESS_CODE)){
		return "Quote Submitted Successfully!! Quote Id : "+supplierQuote.getQuoteId();
		}else
			return "Error occured while Submitting the Quote!!";
	}

}

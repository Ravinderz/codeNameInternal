package com.congun.web.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.SupplierQuoteDao;
import com.congun.web.model.AddEquipment;
import com.congun.web.model.SupplierQuote;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;

@Service
@Transactional
public class SupplierQuoteService {
	private static Logger logger = Logger.getLogger(SupplierQuoteService.class);
	@Autowired
	SupplierQuoteDao supplierDao;
	
	
	public String submitQuote(SupplierQuote supplierQuote){
		logger.info("Entered into SupplierQuoteService.submitQuote method");
		String status = supplierDao.saveQuote(supplierQuote);
		return status;
	}
	
	public String updateQuote(SupplierQuote supplierQuotation){
		logger.info("Entered into SupplierQuoteService.updateQuote method");
		String status = supplierDao.updateQuote(supplierQuotation);
		return status;
	}
	
	public String getQuotationsbySupplier(long supplierId){
		logger.info("Entered into SupplierQuoteService.getQuotationsbySupplier method SupplierId"+supplierId);
		List<SupplierQuote> suppQuotesList = supplierDao.getQuotesbySupplier(supplierId);
		if(suppQuotesList != null)
		return ApplicationUtil.getJsonResponse(suppQuotesList);
		else
			return ResponseConstants.SUPPLIER_FAILURE_CODE;
	}

	public String getQuotationsbyId(long quoteId){
		logger.info("Entered into SupplierQuoteService.getQuotationsbyId method quoteId:"+quoteId);
		SupplierQuote supplierQuote =  supplierDao.getQuotesbyId(quoteId);
		if(supplierQuote != null){
		return ApplicationUtil.getJsonResponse(supplierQuote);
		}
		else
			return ResponseConstants.SUPPLIER_FAILURE_CODE;
	}
	
	public String getQuotationsbyRequirement(long requirementId){
		logger.info("Entered into SupplierQuoteService.getQuotationsbyRequirement method RequirementId:"+requirementId);
		List<SupplierQuote> suppQuotesList = supplierDao.getQuotesbyRequirement(requirementId);
		if(suppQuotesList != null)
		return ApplicationUtil.getJsonResponse(suppQuotesList);
		else
			return ResponseConstants.SUPPLIER_FAILURE_CODE;
	} 
	
	public int getNoOfQuotationsbyRequirement(long requirementId){
		logger.info("Entered into SupplierQuoteService.getNoOfQuotationsbyRequirement method RequirementId:"+requirementId);
		int count = supplierDao.getNoOfQuotesbyRequirement(requirementId);
		if( count > 0)
		return count;
		else
			return 0;
	}
	
	public String addEquipment(AddEquipment equipment) {
		logger.info("Entered into SupplierQuoteService.addEquipment method ");
		return supplierDao.addEquipment(equipment);
	}

	public String updateEquipment(AddEquipment equipment){
		logger.info("Entered into SupplierQuoteService.updateEquipment method ");
		String status = supplierDao.updateEquipment(equipment);
		return status;
	}
	
	public String getAllEquipments(Long supplierId) {
		logger.info("Entered into SupplierQuoteService.getAllEquipments method SupplierId:"+supplierId);
		return ApplicationUtil.getJsonResponse(supplierDao.getAllEquipments(supplierId));
	}

	public String deleteEquipmentById(int equipmentId) {
		logger.info("Entered into SupplierQuoteService.deleteEquipmentById method EquipmentId:"+equipmentId);
		return supplierDao.deleteEquipmentById(equipmentId);
	}
	
	public String getEquipmentById(int equipmentId){
		logger.info("Entered into SupplierQuoteService.getEquipmentById method EquipmentId:"+equipmentId);
		String response = supplierDao.getEquipmentById(equipmentId);
		if(response != null)
		return supplierDao.getEquipmentById(equipmentId);
		else
			return ResponseConstants.EQUIPMENT_FAILURE_CODE;
	}

		
}

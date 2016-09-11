package com.congun.web.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.ContractorRequirementQuoteDAO;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.model.DropDownMaster;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;

@Transactional
@Service
public class ContractorRequirementQuoteService{
	
	@Autowired
	ContractorRequirementQuoteDAO dao;
	
	private static Logger logger = Logger.getLogger(ContractorRequirementQuoteService.class);

	public String saveRequirement(ContractorRequirement requirement) {
		logger.info("Entered into ContractorRequirementQuoteService.saveRequirement method");
		String status = dao.saveRequirement(requirement);
		return status;
	}

	public String getRequirementById(long id) {
		logger.info("Entered into ContractorRequirementQuoteService.getRequirementById method ID:"+id);
		String status = ApplicationUtil.getJsonResponse(dao.getRequirementById(id));
		return status;
	}

	public String updateRequirement(ContractorRequirement requirement) {
		logger.info("Entered into ContractorRequirementQuoteService.updateRequirement method");
		String status = dao.updateRequirement(requirement);
		return status;
	}
	
	public String getAllRequirementsByContractorId(long id,int page,int pSize) {
		logger.info("Entered into ContractorRequirementQuoteService.getAllRequirementsByContractorId method ID:"+id);
		List<ContractorRequirement> reqList = dao.getAllRequirementsByConctractorId(id,page,pSize);
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
		}
	
	public String getAllMappedRequirements(long suppId,int page,int pSize){
		logger.info("Entered into ContractorRequirementQuoteService.getAllMappedRequirements method SupplierID:"+suppId);
		List<ContractorRequirement> reqList = dao.getAllMappedRequirements(suppId,page,pSize);
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
		
	}

	public String getDistinctCategory() {
		logger.info("Entered into ContractorRequirementQuoteService.getDistinctCategory method ");
		List<DropDownMaster> reqList = dao.getDistinctCategory();
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
	}

	public String getEquipmentByCategory(String category) {
		logger.info("Entered into ContractorRequirementQuoteService.getEquipmentByCategory method Category"+category);
		List<DropDownMaster> reqList = dao.getEquipmentByCategory(category);
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
	}

	public String getAllRequirements() {
		logger.info("Entered into ContractorRequirementQuoteService.getAllRequirements method");
		List<ContractorRequirement> reqList = dao.getAllRequirements();
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
		
	}

	public String getRequirementsByequipment(String equipment) {
		logger.info("Entered into ContractorRequirementQuoteService.getRequirementsByequipment method Equipment:"+equipment);
		List<ContractorRequirement> reqList = dao.getRequirementsByequipment(equipment);
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
	}

	public String getTopFiveRequirementsByContId(Long id) {
		logger.info("Entered into ContractorRequirementQuoteService.getTopFiveRequirementsByContId method ID:"+id);
		List<ContractorRequirement> reqList = dao.getTopFiveRequirementsByContId(id);
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
		}
}

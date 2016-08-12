package com.congun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.ContractorRequirementQuoteDAO;
import com.congun.web.model.AddEquipment;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.model.DropDownMaster;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;

@Transactional
@Service
public class ContractorRequirementQuoteService{
	
	@Autowired
	ContractorRequirementQuoteDAO dao;

	public String saveRequirement(ContractorRequirement requirement) {
		String status = dao.saveRequirement(requirement);
		return status;
	}

	public String getRequirementById(long id) {
		String status = ApplicationUtil.getJsonResponse(dao.getRequirementById(id));
		return status;
	}

	public String updateRequirement(ContractorRequirement requirement) {
		String status = dao.updateRequirement(requirement);
		return status;
	}
	
	public String getAllRequirementsByContractorId(long id) {
		
		List<ContractorRequirement> reqList = dao.getAllRequirementsByConctractorId(id);
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
		}
	
	public String getAllMappedRequirements(long suppId){
		
		List<ContractorRequirement> reqList = dao.getAllMappedRequirements(suppId);
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
		
	}

	public String getDistinctCategory() {
		List<DropDownMaster> reqList = dao.getDistinctCategory();
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
	}

	public String getEquipmentByCategory(String category) {
		List<DropDownMaster> reqList = dao.getEquipmentByCategory(category);
		if(reqList != null)
			return ApplicationUtil.getJsonResponse(reqList);
			else
				return ResponseConstants.CONTRACTOR_FAILURE_CODE;
	}

}

package com.congun.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.RequirementQuoteDAO;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.util.ApplicationUtil;

@Transactional
@Service
public class RequirementQuoteService{
	
	@Autowired
	RequirementQuoteDAO dao;

	public void saveRequirement(ContractorRequirement requirement) {
		
		dao.saveRequirement(requirement);
	}

	public String getRequirementById(long id) {
		
		return ApplicationUtil.getJsonResponse(dao.getRequirementById(id));
	}

	public void updateRequirement(ContractorRequirement requirement) {
		
		dao.updateRequirement(requirement);
	}

}

package com.congun.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.RequirementQuoteDAO;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.util.ApplicationUtil;

@Transactional
@Service
public class RequirementQuoteService{
	private static Logger logger = Logger.getLogger(RequirementQuoteService.class);
	@Autowired
	RequirementQuoteDAO dao;

	public void saveRequirement(ContractorRequirement requirement) {
		logger.info("Entered into RequirementQuoteService.saveRequirement method");
		dao.saveRequirement(requirement);
	}

	public String getRequirementById(long id) {
		logger.info("Entered into RequirementQuoteService.getRequirementById method ID:"+id);
		return ApplicationUtil.getJsonResponse(dao.getRequirementById(id));
	}

	public void updateRequirement(ContractorRequirement requirement) {
		logger.info("Entered into RequirementQuoteService.updateRequirement method");
		dao.updateRequirement(requirement);
	}

}

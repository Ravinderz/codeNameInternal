package com.congun.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.congun.web.dao.ContractorRequirementQuoteDAO;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.util.SupplierMapperComponent;

//public class RequirementMappingCronJob extends QuartzJobBean{
@Component
public class RequirementMappingCronJob {
	
	@Autowired
	SupplierMapperComponent mapperComponent;
	@Autowired
	ContractorRequirementQuoteDAO requirementDAO;
	
/*
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
	}
	*/
	@Scheduled(fixedDelay = 60000)
	//@Scheduled(fixedDelay = 60000000)
	public void requirementMapping() throws InterruptedException{
		System.out.println("Entered Crone Job : "+System.currentTimeMillis());
		
		List<ContractorRequirement> requirementList;
		requirementList = requirementDAO.getAllRequirements();
		for(ContractorRequirement requirementResult : requirementList){
			mapperComponent.SupplierMapperPreprocessor(requirementResult);
		}
		
		
		//Thread.sleep(15000);
		
	}

}

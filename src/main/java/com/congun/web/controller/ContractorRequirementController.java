package com.congun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.ContractorRequirement;
import com.congun.web.service.RequirementQuoteService;

@RestController
@RequestMapping("contractor")
public class ContractorRequirementController {
	
	@Autowired
	RequirementQuoteService service;
	
	@RequestMapping(value = "/contractorRequirement" , method = RequestMethod.POST)
	public void contractorRequirement(@RequestBody ContractorRequirement requirement ){
		System.out.println("ENTERED INTO CONTRACTOR REQUIRMENT");
		service.saveRequirement(requirement);	
		}
	
	@RequestMapping(value = "/contractorRequirement/{id}" , method = RequestMethod.GET)
	public String getRequirementDetailsById(@PathVariable Long id ){
		System.out.println("ENTERED INTO CONTRACTOR REQUIRMENT");
		return service.getRequirementById(id);
		}
	
	@RequestMapping(value = "/updateContractorRequirement" , method = RequestMethod.PUT)
	public void contractorRequirementUpdateById(@RequestBody ContractorRequirement requirement){
		System.out.println("ENTERED INTO UPDATE CONTRACTOR REQUIRMENT");
		service.updateRequirement(requirement);
		}
	
	@RequestMapping(value = "/getAllRequirements/{id}" , method = RequestMethod.GET)
	public String getAllRequirementsByContractorId(@PathVariable Long id){
		return service.getAllRequirementsByContractorId(id);
	}

}

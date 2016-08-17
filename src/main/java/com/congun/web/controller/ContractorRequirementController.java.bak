package com.congun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.ContractorRequirement;
import com.congun.web.service.ContractorRequirementQuoteService;

@RestController
@RequestMapping("contractor")
public class ContractorRequirementController {
	
	@Autowired
	ContractorRequirementQuoteService service;
	
	@RequestMapping(value = "/contractorRequirement" , method = RequestMethod.POST)
	public String contractorRequirement(@RequestBody ContractorRequirement requirement ){
		System.out.println("ENTERED INTO CONTRACTOR REQUIRMENT");
		return service.saveRequirement(requirement);	
		}
	
	@RequestMapping(value = "/contractorRequirement/{id}" , method = RequestMethod.GET)
	public String getRequirementDetailsById(@PathVariable Long id ){
		System.out.println("ENTERED INTO CONTRACTOR REQUIRMENT");
		return service.getRequirementById(id);
		}
	
	@RequestMapping(value = "/updateContractorRequirement" , method = RequestMethod.PUT)
	public String updateContractorRequirementById(@RequestBody ContractorRequirement requirement){
		System.out.println("ENTERED INTO UPDATE CONTRACTOR REQUIRMENT");
		return service.updateRequirement(requirement);
		}
	
	@RequestMapping(value = "/getAllRequirements/{id}" , method = RequestMethod.GET)
	public String getAllRequirementsByContractorId(@PathVariable Long id){
		return service.getAllRequirementsByContractorId(id);
	}
	
	@RequestMapping(value = "/getDistinctCategory" , method = RequestMethod.GET)
	public String getDistinctCategory(){
		return service.getDistinctCategory();
	}

	@RequestMapping(value = "/getEquipmentByCategory/{category}" , method = RequestMethod.GET)
	public String getEquipmentByCategory(@PathVariable String category){
		return service.getEquipmentByCategory(category);
	}
	
	@RequestMapping(value="/getmappedrequirements/{supplierId}", method =RequestMethod.GET )
	public String getAllMappedRequirements(@PathVariable("supplierId") long suppId){
		return service.getAllMappedRequirements(suppId);
	}
}

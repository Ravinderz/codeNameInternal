package com.congun.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.ContractorRequirement;
import com.congun.web.service.ContractorRequirementQuoteService;
import com.congun.web.util.ApplicationUtil;

@RestController
@RequestMapping("contractor")
public class ContractorRequirementController {
	  private static Logger logger = Logger.getLogger(ContractorRequirementController.class);
	  
	@Autowired
	ContractorRequirementQuoteService service;
	
	@Autowired
	ApplicationUtil appUtil;
	
	
	@RequestMapping(value = "/contractorRequirement" , method = RequestMethod.POST)
	public String contractorRequirement(@RequestBody ContractorRequirement requirement ){
		logger.info("Entered into ContractorRequirementController.contractorRequirement method");
		return service.saveRequirement(requirement);	
		}
	
	@RequestMapping(value = "/testemail" , method = RequestMethod.GET)
	public void testEmail(){
		System.out.println("Entered test email");	
		appUtil.sendReqEmailToSupp(1,1);
		}
	
	@RequestMapping(value = "/contractorRequirement/{id}" , method = RequestMethod.GET)
	public String getRequirementDetailsById(@PathVariable Long id ){
		logger.info("Entered into ContractorRequirementController.getRequirementDetailsById method  ID:"+id);
		return service.getRequirementById(id);
		}
	
	@RequestMapping(value = "/updateContractorRequirement" , method = RequestMethod.PUT)
	public String updateContractorRequirementById(@RequestBody ContractorRequirement requirement){
		logger.info("Entered into ContractorRequirementController.updateContractorRequirementById method");
		return service.updateRequirement(requirement);
		}
	
	@RequestMapping(value = "/getAllRequirements/{id}" , method = RequestMethod.GET)
	public String getAllRequirementsByContractorId(@PathVariable Long id){
		logger.info("Entered into ContractorRequirementController.getAllRequirementsByContractorId method  ID:"+id);
		return service.getAllRequirementsByContractorId(id);
	}
	
	@RequestMapping(value = "/getDistinctCategory" , method = RequestMethod.GET)
	public String getDistinctCategory(){
		logger.info("Entered into ContractorRequirementController.getDistinctCategory method");
		return service.getDistinctCategory();
	}

	@RequestMapping(value = "/getEquipmentByCategory/{category}" , method = RequestMethod.GET)
	public String getEquipmentByCategory(@PathVariable String category){
		logger.info("Entered into ContractorRequirementController.getEquipmentByCategory method  Category:"+category);
		return service.getEquipmentByCategory(category);
	}
	
	@RequestMapping(value="/getmappedrequirements/{supplierId}", method = RequestMethod.GET )
	public String getAllMappedRequirements(@PathVariable("supplierId") long suppId){
		logger.info("Entered into ContractorRequirementController.getAllMappedRequirements method  SupplierId:"+suppId);
		return service.getAllMappedRequirements(suppId);
	}
	
	@RequestMapping(value="/getAllRequirements" , method = RequestMethod.GET)
	public String getAllRequirements(){
		logger.info("Entered into ContractorRequirementController.getAllRequirements method");
		return service.getAllRequirements();
	}
	
	@RequestMapping(value="/getRequirementsByequipment/{equipment}", method = RequestMethod.GET )
	public String getRequirementsByequipment(@PathVariable("equipment") String equipment){
		logger.info("Entered into ContractorRequirementController.getRequirementsByequipment method  Equipment:"+equipment);
		return service.getRequirementsByequipment(equipment);
	}
	@RequestMapping(value = "/getLatestRequirements/{id}" , method = RequestMethod.GET)
	public String getTopFiveRequirementsByContId(@PathVariable Long id){
		logger.info("Entered into ContractorRequirementController.getTopFiveRequirementsById method  ID:"+id);
		return service.getTopFiveRequirementsByContId(id);
	}
	
}

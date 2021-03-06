package com.congun.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.AddEquipment;
import com.congun.web.model.SupplierQuote;
import com.congun.web.service.SupplierQuoteService;

@RestController
@RequestMapping("supplier")
public class SupplierController {
	private static Logger logger = Logger.getLogger(SupplierController.class);
	@Autowired
	SupplierQuoteService supplierService;
	
	@RequestMapping(value="/submitquote" , method=RequestMethod.POST)
	public 	String submitQuotation(@RequestBody SupplierQuote supplierQuotation){
		logger.info("Entered into SupplierController.submitQuotation method");
		return supplierService.submitQuote(supplierQuotation);
		}
	

	@RequestMapping(value="/updatequote" , method=RequestMethod.PUT)
	public 	String updateQuotation(@RequestBody SupplierQuote supplierQuotation){
		logger.info("Entered into SupplierController.updateQuotation method");
		return supplierService.updateQuote(supplierQuotation);	
	}
	
	@RequestMapping(value="/getquotesbysupplier/{supplierId}" , method=RequestMethod.GET)
	public 	String getQuotationsBySupplier(@PathVariable("supplierId") long suppId,
			@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pSize", defaultValue = "5") int pSize){
		logger.info("Entered into SupplierController.getQuotationsBySupplier method  SupplierId:"+suppId);
		return supplierService.getQuotationsbySupplier(suppId,page,pSize);	
	}
	
	@RequestMapping(value="/getquotes/{quoteId}" , method=RequestMethod.GET)
	public 	String getQuotationsById(@PathVariable("quoteId") long quoteId){
		logger.info("Entered into SupplierController.getQuotationsById method  QuoteId:"+quoteId);
		return supplierService.getQuotationsbyId(quoteId);	
	}
	
	@RequestMapping(value="/getquotesbyrequirement/{requirementId}" , method=RequestMethod.GET)
	public 	String getQuotationsByRequirement(@PathVariable("requirementId") long requirementId,
	@RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "pSize", defaultValue = "5") int pSize){
		logger.info("Entered into SupplierController.getQuotationsByRequirement method  RequirementId:"+requirementId);
		return supplierService.getQuotationsbyRequirement(requirementId,page,pSize);	
	}
	
	@RequestMapping(value="/getnoofquotes/{requirementId}" , method=RequestMethod.GET)
	public 	int getnoOfQuotationsByRequirement(@PathVariable("requirementId") long requirementId){
		logger.info("Entered into SupplierController.getnoOfQuotationsByRequirement method  RequirementId:"+requirementId);
		return supplierService.getNoOfQuotationsbyRequirement(requirementId);	
	}
	
	@RequestMapping(value="/addequipment" , method=RequestMethod.POST)
	public String addEquipment(@RequestBody AddEquipment equipment){
		logger.info("Entered into SupplierController.addEquipment method");
		return supplierService.addEquipment(equipment);
	}
	
	@RequestMapping(value="/updateequipment" , method=RequestMethod.PUT)
	public String updateEquipment(@RequestBody AddEquipment equipment){
		logger.info("Entered into SupplierController.updateEquipment method");
		return supplierService.updateEquipment(equipment);
	}
	
	@RequestMapping(value="/getequipmentbyid/{equipmentId}" ,method=RequestMethod.GET)
	public String getEquipmentById(@PathVariable int equipmentId){
		logger.info("Entered into SupplierController.getEquipmentById method  EquipmentId:"+equipmentId);
		return supplierService.getEquipmentById(equipmentId);
	}
	
	@RequestMapping(value="/getallequipments/{supplierId}" , method=RequestMethod.GET)
	public String getAllEquipments(@PathVariable Long supplierId){
		logger.info("Entered into SupplierController.getAllEquipments method  SupplierId:"+supplierId);
		return supplierService.getAllEquipments(supplierId);
	}
	
	@RequestMapping(value="/deleteEquipmentById/{equipmentId}" ,method=RequestMethod.DELETE)
	public String deleteEquipmentById(@PathVariable int equipmentId){
		logger.info("Entered into SupplierController.deleteEquipmentById method  EquipmentId:"+equipmentId);
		return supplierService.deleteEquipmentById(equipmentId);
	}
	
	@RequestMapping(value="/getquotesbystatus/{requirementId}" , method=RequestMethod.GET)
	public 	String getQuotationsByStatus(@PathVariable("requirementId") long requirementId,@RequestParam String quoteStatus){
		logger.info("Entered into SupplierController.getQuotationsByStatus method  requirementId:"+requirementId+"  quoteStatus:"+quoteStatus);
		return supplierService.getQuotationsByStatus(requirementId,quoteStatus);	
	}
	
	@RequestMapping(value = "/getLatestRequirements/{id}" , method = RequestMethod.GET)
	public String getTopFiveRequirementsBysupId(@PathVariable Long id){
		logger.info("Entered into ContractorRequirementController.getTopFiveRequirementsById method  ID:"+id);
		return supplierService.getTopFiveRequirementsBysupId(id);
	}
	
	@RequestMapping(value = "/checkifquoted" , method = RequestMethod.GET)
	public String checkIfQuotated(@RequestParam Long suppid,@RequestParam Long reqId){
		logger.info("Entered to check if Quotation has been posted by Supplied Id"+suppid+" for Requirement id"+reqId);
		return supplierService.checkIfQuoted(suppid,reqId);
	}
	

}

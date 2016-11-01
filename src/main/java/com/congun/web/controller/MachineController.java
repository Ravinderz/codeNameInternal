package com.congun.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.service.MachineService;
import com.congun.web.util.CongunXLParser;

@RestController
@RequestMapping("machine")
public class MachineController {

	private static Logger logger = Logger.getLogger(MachineController.class);
	@Autowired
	MachineService machineService;
	
	@Autowired
	CongunXLParser parser;
	
	@RequestMapping(value="/getmachines/{category}" , method=RequestMethod.GET)
	public String getmachinebycategory(@PathVariable("category") String category){
		logger.info("Entered into MachineController.getmachinebycategory method  Category:"+category);
		return machineService.getMachineByCategory(category);
	}
	
	@RequestMapping(value="/getMachinesListFromExcel" , method=RequestMethod.GET)
	public void getMachinesListFromExcel(){
		logger.info("Entered into MachineController.getMachinesListFromExcel method");
		String path="/usr/congun/SampleExcel.xlsx";
		parser.getMachinesList(path);
	}
	
	@RequestMapping(value="/getMachineDetailsByModel/{model}/" , method=RequestMethod.GET)
	public String getMachineDetailsByModel(@PathVariable("model") String model){
		logger.info("Entered into MachineController.getMachineDetailsByModel method  Model:"+model);
		return machineService.getMachineDetailsByModel(model);
	}
	
	@RequestMapping(value="/search/{value}" , method=RequestMethod.GET)
	public String getSearchResults(@PathVariable("value") String value){
		logger.info("Entered into MachineController.getSearchResults method   Search value:"+value);
		return machineService.getSearchResults(value);
	}
	
	@RequestMapping(value="/getAllModels" , method=RequestMethod.GET)
	public String getAllModels(){
		logger.info("Entered into MachineController.getAllModels method");
		return machineService.getAllModels();
	}
	
	@RequestMapping(value="/getAllManufacturers" , method=RequestMethod.GET)
	public String getAllManufacturers(){
		logger.info("Entered into MachineController.getAllManufacturers method");
		return machineService.getAllManufacturers();
	}
	
	@RequestMapping(value="/getModelsByMake/{make}" , method=RequestMethod.GET)
	public String getModelsByMake(@PathVariable("make") String make){
		logger.info("Entered into MachineController.getMachineDetailsByModel method  make:"+make);
		return machineService.getModelsByMake(make);
	}
}

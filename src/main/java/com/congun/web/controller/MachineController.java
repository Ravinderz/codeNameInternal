package com.congun.web.controller;

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

	
	@Autowired
	MachineService machineService;
	
	@Autowired
	CongunXLParser parser;
	
	@RequestMapping(value="/getmachines/{category}" , method=RequestMethod.GET)
	public String getmachinebycategory(@PathVariable("category") String category){
		System.out.println("Getting list of available machines for Category :: "+category);
		return machineService.getMachineByCategory(category);
	}
	
	@RequestMapping(value="/getMachinesListFromExcel" , method=RequestMethod.GET)
	public void getMachinesListFromExcel(){
		String path="F:\\congunfiles\\SampleExcel.xlsx";
		parser.getMachinesList(path);
	}
	
	@RequestMapping(value="/getMachineDetailsByModel/{model}" , method=RequestMethod.GET)
	public String getMachineDetailsByModel(@PathVariable("model") String model){
		return machineService.getMachineDetailsByModel(model);
	}
	
	@RequestMapping(value="/search/{value}" , method=RequestMethod.GET)
	public String getSearchResults(@PathVariable("value") String value){
		return machineService.getSearchResults(value);
	}
	
}

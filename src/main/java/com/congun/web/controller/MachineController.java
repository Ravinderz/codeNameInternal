package com.congun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.service.MachineService;

@RestController
@RequestMapping("machine")
public class MachineController {

	
	@Autowired
	MachineService machineService;
	
	@RequestMapping(value="/getmachines/{category}" , method=RequestMethod.GET)
	public String getmachinebycategory(@PathVariable("category") String category){
		System.out.println("Getting list of available machines for Category :: "+category);
		return machineService.getMachineByCategory(category);
	}
}

package com.congun.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.AddEquipment;
import com.congun.web.model.UsedMachineSale;
import com.congun.web.service.UsedMachineSaleService;

@RestController
@RequestMapping("usedmachine")
public class UsedMachineSaleController {

	private static Logger logger = Logger.getLogger(UsedMachineSaleController.class);
	@Autowired
	UsedMachineSaleService saleService;
	
	@RequestMapping(value="/postusedmachines" , method=RequestMethod.POST)
	public String postUsedMachines(@RequestBody UsedMachineSale machineSale){
		logger.info("Entered into UsedMachinesSaleController.postUsedMachines method");
		return saleService.postUsedMachines(machineSale);
	}
	
	
}

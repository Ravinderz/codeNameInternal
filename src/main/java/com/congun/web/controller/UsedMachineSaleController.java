package com.congun.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.UsedMachineMapping;
import com.congun.web.model.UsedMachineSale;
import com.congun.web.service.UsedMachineSaleService;

@RestController
@RequestMapping("usedmachine")
public class UsedMachineSaleController {

	private static Logger logger = Logger
			.getLogger(UsedMachineSaleController.class);
	@Autowired
	UsedMachineSaleService saleService;

	@RequestMapping(value = "/postusedmachines", method = RequestMethod.POST)
	public String postUsedMachines(@RequestBody UsedMachineSale machineSale) {
		logger.info("Entered into UsedMachinesSaleController.postUsedMachines method");
		return saleService.postUsedMachines(machineSale);
	}
	
	@RequestMapping(value = "/deletePostById/{postId}", method = RequestMethod.DELETE)
	public String deletePostById(@PathVariable long postId) {
		logger.info("Entered into UsedMachinesSaleController.deletePostById method+ postId"
				+ postId);
		return saleService.deletePostById(postId);
	}

	@RequestMapping(value = "/getAllPosts", method = RequestMethod.GET)
	public String getAllPosts() {
		logger.info("Entered into UsedMachinesSaleController.getAllPosts method");
		return saleService.getAllPosts();
	}

	@RequestMapping(value = "/getPostsByUserId/{userId}", method = RequestMethod.GET)
	public String getPostsByUserId(@PathVariable long userId) {
		logger.info("Entered into UsedMachinesSaleController.getPostsByUserId method: userId"
				+ userId);
		return saleService.getPostsByUserId(userId);
	}

	@RequestMapping(value = "/interest/{postId}", method = RequestMethod.POST)
	public String UsedMachineInterested(@RequestBody UsedMachineMapping usedMachineMapping,@PathVariable long postId) {
		logger.info("Entered into UsedMachineSaleController.UsedMachineInterested method postId"+postId);
		return saleService.postInterestedUser(usedMachineMapping,postId);
	}

	@RequestMapping(value = "/filterUsedMachines", method = RequestMethod.GET)
	public String filterUsedMachines(@RequestParam(value = "location",required=false ) String location,@RequestParam(value = "equipment",required=false) String equipment,@RequestParam(value = "manufacturer",required=false) String manufacturer) {
		logger.info("Entered into UsedMachineSaleController.filterUsedMachines method");
		return saleService.filterUsedMachines(location,equipment,manufacturer);
	}

}

package com.congun.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.UsedMachineSaleDAO;
import com.congun.web.model.UsedMachineMapping;
import com.congun.web.model.UsedMachineSale;

@Service
@Transactional
public class UsedMachineSaleService {
	
	private static Logger logger = Logger.getLogger(UsedMachineSaleService.class);
	
	@Autowired
	UsedMachineSaleDAO saleDao;
	
	public String postUsedMachines(UsedMachineSale machineSale) {
		logger.info("Entered into UsedMachineSaleService.postUsedMachines method ");
		return saleDao.postUsedMachines(machineSale);
	}
	
	public String getPostById(long postId) {
		logger.info("Entered into UsedMachineSaleService.getPostById method ");
		return saleDao.getPostById(postId);
	}

	public String deletePostById(long postId) {
		logger.info("Entered into UsedMachineSaleService.deletePostById method ");
		return saleDao.deletePostById(postId);
	}

	public String getAllPosts() {
		logger.info("Entered into UsedMachineSaleService.getAllPosts method ");
		return saleDao.getAllPosts();
	}

	public String getPostsByUserId(long userId) {
		logger.info("Entered into UsedMachineSaleService.getPostsByUserId method ");
		return saleDao.getPostsByUserId(userId);
	}
	
	public String postInterestedUser(UsedMachineMapping usedMachineMapping,long postId) {
		logger.info("Entered into UsedMachineSaleService.postInterestedUser method ");
		return saleDao.postInterestedUser(usedMachineMapping,postId);
	}

	public String getInterestedUser(long postId) {
		logger.info("Entered into UsedMachineSaleService.postInterestedUser method ");
		return saleDao.getInterestedUser(postId);
	}
	
	public String filterUsedMachines(String location, String equipment,
			String manufacturer) {
		logger.info("Entered into UsedMachineSaleService.filterUsedMachines method ");
		return saleDao.filterUsedMachines(location,equipment,manufacturer);
	}
	
	public String getRelatedUsedEquipments(String category, String equipmentName) {
		logger.info("Entered into UsedMachineSaleService.getRelatedUsedEquipments method category"+category+" "+"equipmentName"+equipmentName);
		return saleDao.getRelatedUsedEquipments(category,equipmentName);
	}

}

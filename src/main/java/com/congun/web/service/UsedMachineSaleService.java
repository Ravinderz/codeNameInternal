package com.congun.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.congun.web.dao.UsedMachineSaleDAO;
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

}

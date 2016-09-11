package com.congun.web.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.MachineDAO;
import com.congun.web.model.Machines;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;

@Service
@Transactional
public class MachineService {
	private static Logger logger = Logger.getLogger(MachineService.class);
	
	@Autowired
	MachineDAO machineDao;
	
	public String getMachineByCategory(String category) {
		logger.info("Entered into MachineService.getMachineByCategory method Category:"+category);
		Machines machine = machineDao.getMachineDetails(category);
		if(machine != null){
			return ApplicationUtil.getJsonResponse(machine);	
		}else
			return ResponseConstants.MACHINE_FAILURE_CODE;
		
		
	}

	public String getMachineDetailsByModel(String model) {
		logger.info("Entered into MachineService.getMachineDetailsByModel method model:"+model);
		Machines machine = machineDao.getMachineDetailsByModel(model);
		if(machine != null){
			return ApplicationUtil.getJsonResponse(machine);	
		}else
			return ResponseConstants.MACHINE_FAILURE_CODE;
	}

	public String getSearchResults(String value) {
		logger.info("Entered into MachineService.getSearchResults method Search value:"+value);
		List<Machines> machine = machineDao.getSearchResults(value);
		if(machine != null){
			return ApplicationUtil.getJsonResponse(machine);	
		}else
			return ResponseConstants.MACHINE_FAILURE_CODE;
	}

}

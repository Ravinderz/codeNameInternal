package com.congun.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.dao.MachineDAO;
import com.congun.web.dao.UserDao;
import com.congun.web.model.Machines;
import com.congun.web.model.User;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;

@Service
@Transactional
public class MachineService {
	
	@Autowired
	MachineDAO machineDao;
	
	public String getMachineByCategory(String category) {
		System.out.println("Getting details from ServiceImpl : "+category);
		Machines machine = machineDao.getMachineDetails(category);
		if(machine != null){
			return ApplicationUtil.getJsonResponse(machine);	
		}else
			return ResponseConstants.MACHINE_FAILURE_CODE;
		
		
	}

	public String getMachineDetailsByModel(String model) {
		Machines machine = machineDao.getMachineDetailsByModel(model);
		if(machine != null){
			return ApplicationUtil.getJsonResponse(machine);	
		}else
			return ResponseConstants.MACHINE_FAILURE_CODE;
	}

}

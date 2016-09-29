package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.AddEquipment;
import com.congun.web.model.UsedMachineSale;
import com.congun.web.util.ResponseConstants;

@Repository
public class UsedMachineSaleDAO {
	
	private static Logger logger = Logger.getLogger(UsedMachineSaleDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public String postUsedMachines(UsedMachineSale machineSale) {
		logger.info("Entered into UsedMachineSaleDAO.postUsedMachines method");
		try {
			Date date = new Date();
			Timestamp currTime = new Timestamp(date.getTime());
			machineSale.setPostedTime(currTime);
			getSession().saveOrUpdate(machineSale);
			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}

	}
}

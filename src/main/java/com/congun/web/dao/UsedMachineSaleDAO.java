package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.UsedMachineMapping;
import com.congun.web.model.UsedMachineSale;
import com.congun.web.util.ApplicationUtil;
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
			machineSale.setActiveFlag(1);
			getSession().saveOrUpdate(machineSale);
			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}

	}

	@Transactional
	public String postInterestedUser(UsedMachineMapping usedMachineMapping,long postId) {
		logger.info("Entered into UsedMachineSaleDAO.postInterestedUser method");
		try {
			Date date = new Date();
			Timestamp currTime = new Timestamp(date.getTime());
			usedMachineMapping.setPostId(postId);
			usedMachineMapping.setCreatedtime(currTime);
			usedMachineMapping.setActiveFlag(1);
			usedMachineMapping.setUpdatedtime(currTime);
			getSession().saveOrUpdate(usedMachineMapping);
			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}

	}

	@Transactional
	public String deletePostById(long postId) {
		logger.info("Entered into UsedMachineSaleDAO.deletePostById method");
		try {
			Criteria criteria = getSession().createCriteria(
					UsedMachineSale.class);
			criteria.add(Restrictions.eq("postId", postId));
			UsedMachineSale usedMachineSale = (UsedMachineSale) criteria
					.uniqueResult();
			getSession().delete(usedMachineSale);
			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}
	}

	@Transactional
	public String getAllPosts() {
		try {
			Criteria criteria = getSession().createCriteria(
					UsedMachineSale.class);
			List<UsedMachineSale> list = criteria.addOrder(
					Order.desc("postedTime")).list();
			return ApplicationUtil.getJsonResponse(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getPostsByUserId(long userId) {
		try {
			Criteria criteria = getSession().createCriteria(
					UsedMachineSale.class);
			List<UsedMachineSale> list = criteria
					.add(Restrictions.eq("userId", userId))
					.addOrder(Order.desc("postedTime")).list();
			return ApplicationUtil.getJsonResponse(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

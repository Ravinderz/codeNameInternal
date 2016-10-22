package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			Criteria criteria = getSession().createCriteria(
					UsedMachineMapping.class);
			criteria.add(Restrictions.eq("postId", postId));
			criteria.add(Restrictions.eq("username", usedMachineMapping.getUsername()));
			if(criteria.list().size() != 0 || !criteria.list().isEmpty()){
				return ResponseConstants.SUPPLIER_SUCCESS_CODE;
			}else{
			Date date = new Date();
			Timestamp currTime = new Timestamp(date.getTime());
			usedMachineMapping.setPostId(postId);
			usedMachineMapping.setCreatedtime(currTime);
			usedMachineMapping.setActiveFlag(1);
			usedMachineMapping.setUpdatedtime(currTime);
			getSession().saveOrUpdate(usedMachineMapping);
			
			Criteria InterestedCount = getSession().createCriteria(
					UsedMachineMapping.class);
			InterestedCount.add(Restrictions.eq("postId", postId));
			
			
			
			int count = new HashSet(InterestedCount.list()).size();
			
			String hqlQuery = "update UsedMachineSale set interestedPplCount = :count where postId = :postId";
			int result = getSession().createQuery(hqlQuery)
					     .setParameter("count", count)
					     .setParameter("postId", postId)
					     .executeUpdate();
			System.out.println("result : "+result);
			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}

	}

	@Transactional
	public String getInterestedUser(long postId) {
		logger.info("Entered into UsedMachineSaleDAO.postInterestedUser method");
		try {
			Criteria criteria = getSession().createCriteria(
					UsedMachineMapping.class);
			criteria.add(Restrictions.eq("postId", postId));
			Set<UsedMachineSale> interestedPpl = new HashSet<UsedMachineSale>(criteria.list());
			return ApplicationUtil.getJsonResponse(interestedPpl);
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

	@Transactional
	public String filterUsedMachines(String location, String equipment,
			String manufacturer) {
		String delimiter = ":";
		String[] locationlist = null;
		String[] equipmentlist =null;
		String[] manufacturerlist=null;
		if(location!=null){
			locationlist = location.split(delimiter);
		}
		if(equipment!=null){
		 equipmentlist = equipment.split(delimiter);
		}
		if(manufacturer!=null){
		 manufacturerlist = manufacturer.split(delimiter);
		}
		System.out.println();
		try {
			Criteria criteria = getSession().createCriteria(
					UsedMachineSale.class);
			if( locationlist!= null && locationlist.length>0){
				criteria.add(Restrictions.in("location",Arrays.asList(locationlist)));
				
			}
			//if(equipmentlist.length>0){
			if(equipmentlist!=null && equipmentlist.length>0){
				criteria.add(Restrictions.in("equipmentName",Arrays.asList(equipmentlist)));
			}
			if(manufacturerlist!=null && manufacturerlist.length>0){
				criteria.add(Restrictions.in("manufacturer",Arrays.asList(manufacturerlist)));
			}
			List<UsedMachineSale> list = criteria.list();
			return ApplicationUtil.getJsonResponse(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

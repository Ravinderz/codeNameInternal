package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.ContractorRequirement;
import com.congun.web.model.DropDownMaster;
import com.congun.web.model.MappingObject;
import com.congun.web.model.MappingReqSort;
import com.congun.web.util.ResponseConstants;
import com.congun.web.util.SupplierMapperComponent;

@Repository
@Transactional
public class ContractorRequirementQuoteDAO {
	private static Logger logger = Logger
			.getLogger(ContractorRequirementQuoteDAO.class);
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	SupplierMapperComponent mapperComponent;
	
	@Autowired
	SupplierQuoteDao supplierDao;

	@Transactional
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/* To store contractor requirements into contractorrequirement table */
	@SuppressWarnings("deprecation")
	public String saveRequirement(ContractorRequirement requirement) {
		logger.info("Entered into ContractorRequirementQuoteDAO.saveRequirement method");
		try {
			java.util.Date date = new java.util.Date();
			Timestamp time = new Timestamp(date.getTime());
			requirement.setActiveFlag(1);
			requirement.setCreatedTime(time);
			requirement.setUpdatedTime(time);
			sessionFactory.getCurrentSession().saveOrUpdate(requirement);

			/*
			 * if (mapperComponent.SupplierMapperPreprocessor(requirement)) {
			 * logger.info("Mapping the Suppliers for submitted Requirement :" +
			 * requirement.getRequirementId()); } else {
			 * logger.info("Could Not start Mapping for requirement: " +
			 * requirement.getRequirementId()); }
			 */

			return ResponseConstants.CONTRACTOR_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.CONTRACTOR_EXCEPTION_CODE;
		}
	}

	/* To get requirement details by requirement Id */
	public ContractorRequirement getRequirementById(long id) {
		logger.info("Entered into ContractorRequirementQuoteDAO.getRequirementById method  ID:"
				+ id);
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ContractorRequirement.class);
			criteria.add(Restrictions.eq("requirementId", id));
			ContractorRequirement reqObject = (ContractorRequirement) criteria
					.uniqueResult();
			if(reqObject == null){
				logger.info("Requirement Object with Id "+id+"Doesn't exist!!");
				return null;
			}else{
				logger.info("Requirement Object with Id "+id+"Doesn't exist!!");
			return reqObject;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/* To update contractor requirements into contractorrequirement table */
	public String updateRequirement(ContractorRequirement requirement) {
		logger.info("Entered into ContractorRequirementQuoteDAO.updateRequirement method");
		try {
			// ContractorRequirement existingReq = (ContractorRequirement)
			// getSession().createCriteria(ContractorRequirement.class).add(Restrictions.eq("requirementId",
			// requirement.getRequirementId()));
			java.util.Date date = new java.util.Date();
			Timestamp time = new Timestamp(date.getTime());
			// requirement.setCreatedTime(existingReq.getCreatedTime());
			requirement.setUpdatedTime(time);
			sessionFactory.getCurrentSession().saveOrUpdate(requirement);

			if (mapperComponent.SupplierMapperPreprocessor(requirement)) {
				logger.info("Mapping the Suppliers for submitted Requirement :"
						+ requirement.getRequirementId());
			} else {
				logger.info("Could Not start Mapping for requirement: "
						+ requirement.getRequirementId());
			}

			return ResponseConstants.CONTRACTOR_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.CONTRACTOR_EXCEPTION_CODE;
		}
	}

	/* To get the list of requirments by contractorid */
	public List<ContractorRequirement> getAllRequirementsByConctractorId(
			long id, int page, int pSize) {
		logger.info("Entered into ContractorRequirementQuoteDAO.getAllRequirementsByConctractorId method");
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ContractorRequirement.class);
			criteria.setFirstResult((page - 1) * pSize);
			criteria.setMaxResults(pSize);
			criteria.add(Restrictions.eq("contractorId", id)).add(
					Restrictions.eq("activeFlag", 1)).addOrder(Order.desc("createdTime"));;
			List<ContractorRequirement> reqList = criteria.list();
			return reqList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ContractorRequirement> getAllMappedRequirements(long suppId,int page,int pSize) {
		logger.info("Entered into ContractorRequirementQuoteDAO.getAllMappedRequirements method  SupplierID:"
				+ suppId);
		try {
			List reqList = new ArrayList<ContractorRequirement>();
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(MappingObject.class);
			criteria.setFirstResult((page - 1) * pSize);
			criteria.setMaxResults(pSize);
			criteria.setProjection(Projections.property("requirementId"));
			List mappedRequirementsList = (ArrayList) (criteria
					.add(Restrictions.eq("supplierId", suppId))).list();
			List quotationsList = (ArrayList) supplierDao.getQuotesbySupplier(suppId);
			if (mappedRequirementsList.size() > 0) {
				logger.info("There Are Mapped Requirements for SupplierID: "
						+ suppId);
				for (Object obj : mappedRequirementsList) {
					ContractorRequirement requirement = getRequirementById(((Long) obj)
							.longValue());
					reqList.add(requirement);
				}
				
				
				
			}
			
		Collections.sort(reqList,new MappingReqSort());
			return reqList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String updateNoOfQuotes(long requirementId) {
		logger.info("Entered into ContractorRequirementQuoteDAO.updateNoOfQuotes method  requirementId:"
				+ requirementId);
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ContractorRequirement.class);
			ContractorRequirement contractorRequirement = (ContractorRequirement) criteria
					.add(Restrictions.eq("requirementId", requirementId))
					.add(Restrictions.eq("activeFlag", 1)).list().get(0);
			contractorRequirement.setNoofquotes(contractorRequirement
					.getNoofquotes() + 1);
			sessionFactory.getCurrentSession().saveOrUpdate(
					contractorRequirement);
			return ResponseConstants.CONTRACTOR_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.CONTRACTOR_EXCEPTION_CODE;
		}
	}

	public List<DropDownMaster> getDistinctCategory() {
		logger.info("Entered into ContractorRequirementQuoteDAO.getDistinctCategory method ");
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(DropDownMaster.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("equipmentCategory")));
			List<DropDownMaster> list = criteria.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DropDownMaster> getEquipmentByCategory(String category) {
		logger.info("Entered into ContractorRequirementQuoteDAO.getEquipmentByCategory method ");
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(DropDownMaster.class);
			criteria.setProjection(Projections.property("equipmentName"));
			criteria.add(Restrictions.eq("equipmentCategory", category));
			List<DropDownMaster> list = criteria.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ContractorRequirement> getAllRequirements() {
		logger.info("Entered into ContractorRequirementQuoteDAO.getAllRequirements method ");
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ContractorRequirement.class);
			criteria.addOrder(Order.desc("requirementId"));
			criteria.setMaxResults(20);
			List<ContractorRequirement> list = criteria.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ContractorRequirement> getRequirementsByequipment(
			String equipment) {
		logger.info("Entered into ContractorRequirementQuoteDAO.getRequirementsByequipment method equipment:"
				+ equipment);
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ContractorRequirement.class);
			criteria.add(Restrictions.eq("equipmentName", equipment));
			List<ContractorRequirement> list = criteria.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ContractorRequirement> getTopFiveRequirementsByContId(Long id) {
		logger.info("Entered into ContractorRequirementQuoteDAO.getTopFiveRequirementsByContId method ");
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ContractorRequirement.class);
			criteria.addOrder(Order.desc("createdTime"));
			criteria.add(Restrictions.eq("contractorId", id));
			criteria.setMaxResults(5);
			List<ContractorRequirement> list = criteria.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

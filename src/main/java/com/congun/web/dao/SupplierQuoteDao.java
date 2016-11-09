package com.congun.web.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.congun.web.model.AddEquipment;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.model.MappingObject;
import com.congun.web.model.SupplierQuote;
import com.congun.web.model.User;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.ResponseConstants;

@Repository
public class SupplierQuoteDao {

	private static Logger logger = Logger.getLogger(SupplierQuoteDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	public ContractorRequirementQuoteDAO requirementDAO;

	@Autowired
	public MappingObject mappingobject;

	@Autowired
	public UserDao userdao;

	@Autowired
	public ApplicationUtil appUtil;

	@Transactional
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public String saveQuote(SupplierQuote supplierQuote) {
		logger.info("Entered into SupplierQuoteDao.saveQuote method ");
		try {
			long reqId = supplierQuote.getRequirementId();
			Date date = new Date();
			Timestamp currTime = new Timestamp(date.getTime());
			supplierQuote.setCreatedTime(currTime);
			supplierQuote.setUpdatedTime(currTime);
			supplierQuote.setActiveFlag(1);
			requirementDAO.updateNoOfQuotes(supplierQuote.getRequirementId());
			getSession().saveOrUpdate(supplierQuote);

			User user = getEmailIdByReqId(reqId);
			ContractorRequirement contractorReq = requirementDAO
					.getRequirementById(reqId);
			String title = contractorReq.getTitle();
			String email = user.getUsername();
			String name = supplierQuote.getQuotePostedByName();
			String phoneNo = supplierQuote.getMobileNumber();
			appUtil.sendQuoteEmailToContractor(email, name, phoneNo, title);
			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}
	}

	private User getEmailIdByReqId(long reqId) {
		// String emailId = "" ;
		User userDeatils = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ContractorRequirement.class);
			criteria.setProjection(Projections.property("contractorId"));
			List<Long> CotractId = (ArrayList) (criteria.add(Restrictions.eq(
					"requirementId", reqId))).list();
			if (CotractId.size() > 0) {
				logger.info("Contractor Id for the requirement ID: " + reqId);
				for (Long contractorReq : CotractId) {
					// long contractorId = contractorReq.getContractorId();
					userDeatils = userdao.getUserById(contractorReq);
					// emailId = userDeatils.getUsername();
					// logger.info("User Email Id"+emailId);
				}
			}
			return userDeatils;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public String updateQuote(SupplierQuote supplierQuote) {
		logger.info("Entered into SupplierQuoteDao.updateQuote method ");
		try {
			// SupplierQuote existingQuote = (SupplierQuote)
			// getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("quoteId",
			// supplierQuote.getQuoteId())).list().get(0);
			Date date = new Date();
			Timestamp currTime = new Timestamp(date.getTime());
			// supplierQuote.setCreatedTime(existingQuote.getCreatedTime());
			supplierQuote.setUpdatedTime(currTime);
			getSession().saveOrUpdate(supplierQuote);

			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<SupplierQuote> getQuotesbySupplier(long supplierId,int page,int pSize) {
		logger.info("Entered into SupplierQuoteDao.getQuotesbySupplier method supplierId:"
				+ supplierId);
		try {
			List<SupplierQuote> supplierQuotationList = (ArrayList<SupplierQuote>) getSession()
					.createCriteria(SupplierQuote.class).setFirstResult((page - 1) * pSize).setMaxResults(pSize)
					.add(Restrictions.eq("quotePostedById", supplierId)).addOrder(Order.desc("createdTime")).list();
			return supplierQuotationList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<SupplierQuote> getQuotesbySupplier(long supplierId) {
		logger.info("Entered into SupplierQuoteDao.getQuotesbySupplier method supplierId:"
				+ supplierId);
		try {
			List<SupplierQuote> supplierQuotationList = (ArrayList<SupplierQuote>) getSession()
					.createCriteria(SupplierQuote.class)
					.add(Restrictions.eq("quotePostedById", supplierId)).list();
			return supplierQuotationList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<SupplierQuote> getQuotesbyRequirement(long requirementId,
			int page, int pSize) {
		logger.info("Entered into SupplierQuoteDao.getQuotesbyRequirement method requirementId:"
				+ requirementId);
		try {
			List<SupplierQuote> supplierQuotationList = (ArrayList<SupplierQuote>) getSession()
					.createCriteria(SupplierQuote.class)
					.setFirstResult((page - 1) * pSize).setMaxResults(pSize)
					.add(Restrictions.eq("requirementId", requirementId)).addOrder(Order.desc("createdTime"))
					.list();

			return supplierQuotationList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public int getNoOfQuotesbyRequirement(long requirementId) {
		logger.info("Entered into SupplierQuoteDao.getNoOfQuotesbyRequirement method requirementId:"
				+ requirementId);
		try {
			List<SupplierQuote> supplierQuotationList = (ArrayList<SupplierQuote>) getSession()
					.createCriteria(SupplierQuote.class)
					.add(Restrictions.eq("requirementId", requirementId))
					.list();
			return supplierQuotationList.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Transactional
	public SupplierQuote getQuotesbyId(long Id) {
		logger.info("Entered into SupplierQuoteDao.getNoOfQuotesbyRequirement method Id:"
				+ Id);
		try {
			SupplierQuote supplierQuotation = (SupplierQuote) getSession()
					.createCriteria(SupplierQuote.class)
					.add(Restrictions.eq("quoteId", Id)).list().get(0);

			if (supplierQuotation != null) {
				logger.info("Got a record");
			} else
				logger.info("Supplier quote is null");
			return supplierQuotation;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public String addEquipment(AddEquipment equipment) {
		logger.info("Entered into SupplierQuoteDao.addEquipment method");
		try {
			Date date = new Date();
			Timestamp currTime = new Timestamp(date.getTime());
			equipment.setCreatedTime(currTime);
			getSession().saveOrUpdate(equipment);
			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}

	}

	@Transactional
	public String updateEquipment(AddEquipment equipment) {
		logger.info("Entered into SupplierQuoteDao.updateEquipment method");
		try {
			Date date = new Date();
			Timestamp currTime = new Timestamp(date.getTime());
			equipment.setUpdatedTime(currTime);
			logger.info("Entered DAO to update Equipment :"
					+ equipment.getEquipmentId());
			
			getSession().saveOrUpdate(equipment);

			return ResponseConstants.EQUIPMENT_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.EQUIPMENT_EXCEPTION_CODE;
		}
	}

	@Transactional
	public String getEquipmentDetails(long supplierId) {
		logger.info("Entered into SupplierQuoteDao.getEquipmentDetails method  supplierId:"
				+ supplierId);
		logger.info("Getting Equipment details from DB :" + supplierId);
		try {
			List<AddEquipment> addEquipment = getSession()
					.createCriteria(AddEquipment.class)
					.add(Restrictions.eq("supplierId", supplierId)).list();
			return ApplicationUtil.getJsonResponse(addEquipment);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public String getEquipmentById(int equipmentId) {
		logger.info("Entered into SupplierQuoteDao.getEquipmentById method  equipmentId:"
				+ equipmentId);
		logger.info("Getting Equipment Details from DB :" + equipmentId);
		try {

			AddEquipment equipment = (AddEquipment) getSession()
					.createCriteria(AddEquipment.class)
					.add(Restrictions.eq("equipmentId", equipmentId)).list()
					.get(0);

			if (equipment != null) {
				return ApplicationUtil.getJsonResponse(equipment);
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public List<AddEquipment> getAllEquipments(Long supplierId) {
		logger.info("Entered into SupplierQuoteDao.getAllEquipments method  supplierId:"
				+ supplierId);
		try {
			Criteria criteria = getSession().createCriteria(AddEquipment.class);
			criteria.add(Restrictions.eq("supplierId", supplierId));
			List<AddEquipment> reqList = criteria.list();
			return reqList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public String deleteEquipmentById(int equipmentId) {
		logger.info("Entered into SupplierQuoteDao.deleteEquipmentById method  equipmentId:"
				+ equipmentId);
		try {
			Criteria criteria = getSession().createCriteria(AddEquipment.class);
			criteria.add(Restrictions.eq("equipmentId", equipmentId));
			AddEquipment equipment = (AddEquipment) criteria.uniqueResult();
			getSession().delete(equipment);
			return ResponseConstants.SUPPLIER_SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseConstants.SUPPLIER_EXCEPTION_CODE;
		}

	}

	@Transactional
	public List<AddEquipment> getEquipmentByCategory(
			ContractorRequirement requirement) {
		logger.info("Entered into SupplierQuoteDao.getEquipmentByCategory method");
		try {
			Criteria criteria = getSession().createCriteria(AddEquipment.class);

			List<AddEquipment> machinesList = (ArrayList<AddEquipment>) criteria
					.add(Restrictions.eq("equipmentCategory",
							requirement.getEquipmentCategory()))
					.add(Restrictions.eq("equipment",
							requirement.getEquipmentName())).list();

			return machinesList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List filterSupplierIds(Set mappedSuppliers, long requirementId) {
		logger.info("Entere the filter Mappers method");
		try {
			String SupplierList = null;
			Criteria criteria = getSession()
					.createCriteria(MappingObject.class);
			// List existingMappedIds =
			// (ArrayList<Long>)criteria.setProjection(Projections.distinct(Projections.property("supplierId"))).list();
			List existingMappedIds = criteria.add(
					Restrictions.eq("requirementId", requirementId)).list();
			if (existingMappedIds.size() > 0) {
				return existingMappedIds;
			}
			return existingMappedIds;
			// return existingMappedIds;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void updateMappingObjects(Set<Long> mapperSuppliers,
			long requirementId) {
		logger.info("Entered into SupplierQuoteDao.updateMappingObjects method");
		String suppId = "";
		try {
			if (mapperSuppliers.size() > 0) {
				Criteria criteria = getSession().createCriteria(
						MappingObject.class);
				for (Long Id : mapperSuppliers) {
					// suppId = suppId+Id.longValue();
					// suppId = suppId+",";
					MappingObject mappedobject = new MappingObject();
					mappedobject.setRequirementId(requirementId);
					mappedobject.setSupplierId(Id);
					getSession().save(mappedobject);
					// send email
					appUtil.sendReqEmailToSupp(mappedobject.getSupplierId(),
							requirementId);

				}

			} else
				logger.info("Received Empty Mapped Suppliers for Requirement :"
						+ requirementId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Ëxception Occured while updating the Mapped Objects for Requirement Id: "
					+ requirementId);
		}
	}

	@Transactional
	public List<Long> checkRequirementMapping(long reqId) {
		Criteria criteria = getSession().createCriteria(MappingObject.class);
		criteria.setProjection(Projections.property("supplierId"));
		List<Long> result = criteria.add(
				Restrictions.eq("requirementId", reqId)).list();
		return result;

	}

	@Transactional
	public List<SupplierQuote> getQuotationsByStatus(long requirementId,
			String quoteStatus) {
		logger.info("Entered into SupplierQuoteDao.getQuotationsByStatus method requirementId:"
				+ requirementId);
		try {
			// List<SupplierQuote> supplierQuotationList =
			// (ArrayList<SupplierQuote>)
			// getSession().createCriteria(SupplierQuote.class).add(Restrictions.eq("requirementId",
			// requirementId)).list();
			Criteria criteria = getSession()
					.createCriteria(SupplierQuote.class);
			criteria.add(Restrictions.eq("requirementId", requirementId));
			criteria.add(Restrictions.eq("quoteStatus", quoteStatus)).addOrder(Order.desc("createdTime"));;
			List<SupplierQuote> supplierQuotationList = criteria.list();
			return supplierQuotationList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SupplierQuote> getTopFiveRequirementsBysupId(Long id) {
		logger.info("Entered into SupplierQuoteDao.getTopFiveRequirementsBysupId method ");
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(SupplierQuote.class);
			criteria.addOrder(Order.desc("createdTime"));
			criteria.add(Restrictions.eq("quotePostedById", id));
			criteria.setMaxResults(5);
			List<SupplierQuote> list = criteria.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean checkIfQuoted(Long suppId,Long reqId){
		logger.info("Entered into DAO to check if quoted for ReqId :"+reqId);
		try{
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SupplierQuote.class);
			criteria.add(Restrictions.eq("quotePostedById", suppId));
			criteria.add(Restrictions.eq("requirementId", reqId));
			List<SupplierQuote> supplierQuotationList = criteria.list();
			if(supplierQuotationList.size() >0){
				return true;
			}else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}


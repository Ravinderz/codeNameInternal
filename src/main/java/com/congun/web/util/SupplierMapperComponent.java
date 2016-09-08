package com.congun.web.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.congun.web.dao.SupplierQuoteDao;
import com.congun.web.dao.UserDao;
import com.congun.web.model.AddEquipment;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.model.MappingObject;
import com.congun.web.model.User;

@Repository
public class SupplierMapperComponent {
	
private static Logger logger = Logger.getLogger(SupplierMapperComponent.class);
@Autowired
private SessionFactory sessionFactory;

@Autowired
private SupplierQuoteDao quoteDAO;

@Autowired
private UserDao userDAO;


	public boolean SupplierMapperPreprocessor(ContractorRequirement requirement) {
		logger.info("Entered into SupplierMapperComponent.SupplierMapperPreprocessor method");
		System.out.println("ReqID:"+requirement.getRequirementId());

		if (requirement.getEquipmentCategory() != null
				&& requirement.getEquipmentName() != null) {
			
			getSupplierDetails(requirement);

			/*MapSupplier supplierMapper = new MapSupplier(requirement);
			supplierMapper.setDaemon(true);
			supplierMapper.start();*/
			return true;
		}

		return false;
	}

/*	class MapSupplier extends Thread {
		
		private boolean running = true;
		private boolean quit = false;
		
		public MapSupplier() {
			// TODO Auto-generated constructor stub
		}

		ContractorRequirement requirement;

		MapSupplier(ContractorRequirement requirement) {
			// TODO Auto-generated constructor stub
			this.requirement = requirement;
		}
		
		synchronized void quit() {
			this.quit = true;
			notify();
		}
		

		@Override
		public void run() {
			
			while (true) {
				synchronized (this) {
					if (this.quit) {
						this.running = false;
						break;
					}
				}
			
			getSupplierDetails(requirement);
			quit();

		 //}
		}

	}*/

	public void getSupplierDetails(ContractorRequirement requirement) {
		logger.info("Entered into SupplierMapperComponent.getSupplierDetails method  requirement:"+requirement);
		System.out.println("Entered into SupplierMapperComponent.getSupplierDetails method  requirement:"+requirement.getRequirementId());
		Set<Long> mappedSupplierIds = new HashSet<Long>();
		int supplierScore;

		List<AddEquipment> machinesList = quoteDAO.getEquipmentByCategory(requirement);

		for (AddEquipment equipment : machinesList) {
			supplierScore = 0;

			/*if (requirement.getManufacturer() != null
					|| requirement.getManufacturer().length > 0) {
				for (String manufacturer : requirement.getManufacturer()) {
					if (equipment.getManufacturer().equals(manufacturer)) {
						// supplierScore ++;
						mappedSupplierIds.add(equipment.getSupplierId());
					}else
						mappedSupplierIds.remove(equipment.getSupplierId());
				}
			}

			if (requirement.getSpecificationModel() != null) {
				if (equipment.getModel().equals(
						requirement.getSpecificationModel())) {
					// supplierScore ++;
					mappedSupplierIds.add(equipment.getSupplierId());
				}else
					mappedSupplierIds.remove(equipment.getSupplierId());
			}

			if (requirement.getEquipmentManufactureYear() != 0) {

				if (equipment.getYearOfManufacturing() == requirement
						.getEquipmentManufactureYear())
				{
					mappedSupplierIds.add(equipment.supplierId);
				}else
					mappedSupplierIds.remove(equipment.getSupplierId());
			}

			if (requirement.getSpecificationCapacity() != null) {
				if (equipment.getCapacity().equals(
						requirement.getSpecificationCapacity())) {
					mappedSupplierIds.add(equipment.supplierId);
				}else
					mappedSupplierIds.remove(equipment.getSupplierId());
			}*/

			/*if ((requirement.getManufacturer() == null || requirement
					.getManufacturer().length == 0)
					&& requirement.getSpecificationModel() == null
					&& requirement.getEquipmentManufactureYear() == 0
					&& requirement.getSpecificationCapacity() == null) {
				*/
			mappedSupplierIds.add(equipment.getSupplierId());
/*			List<Long> supplierIds = quoteDAO.checkRequirementMapping(requirement.getRequirementId());
			for(long resultId : supplierIds){
			if(resultId != equipment.getSupplierId()){
				logger.info("SUPPLIER ID FROM MAPPING OBJECT TABLE : "+resultId);
				System.out.println("SUPPLIER ID FROM MAPPING OBJECT TABLE : "+resultId);
				logger.info("SUPPLIER ID FROM EQUIPMENT TABLE : "+equipment.getSupplierId());
				System.out.println("SUPPLIER ID FROM EQUIPMENT TABLE : "+equipment.getSupplierId());
				mappedSupplierIds.add(equipment.getSupplierId());
				logger.info("REQUIREMENT HAS BEEN MAPPED");	
				System.out.println("REQUIREMENT HAS BEEN MAPPED");
			}else{
				logger.info("REQUIREMENT HAS BEEN ALREADY MAPPED FOR "+resultId);
			}
			}*/
				

			//}
		}
		
		if(mappedSupplierIds.size() > 0){
		sendNotifications(mappedSupplierIds,requirement.getRequirementId());
		}
	}
	
	public void sendNotifications(Set mappedSupplierIds,long requirementId){
		logger.info("Entered into SupplierMapperComponent.sendNotifications method  ");
		mappedSupplierIds = filterMappedSupplierids(mappedSupplierIds,requirementId);
		
		Iterator itr = mappedSupplierIds.iterator();
		while(itr.hasNext()){
			Long supplierId =  (Long)itr.next();
			User user = userDAO.getUserById(supplierId.intValue());
			
		}
		
	}
	
	public Set filterMappedSupplierids(Set mappedSuppliers,long requirementId){
		logger.info("Entered into SupplierMapperComponent.filterMappedSupplierids method requirementId: "+requirementId);
		if(mappedSuppliers.size() > 0){
		
			List existingIds = quoteDAO.filterSupplierIds(mappedSuppliers,requirementId);
			if(existingIds != null || existingIds.size() > 0){
			for(Object Id: existingIds){
				mappedSuppliers.remove(((MappingObject)Id).getSupplierId());
			}
			quoteDAO.updateMappingObjects(mappedSuppliers,requirementId);
			return mappedSuppliers;
			}else{
			
			quoteDAO.updateMappingObjects(mappedSuppliers,requirementId);
			return mappedSuppliers;
			}
		}
		return null;
	}
	
}

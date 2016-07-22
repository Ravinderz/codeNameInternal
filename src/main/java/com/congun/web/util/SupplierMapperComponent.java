package com.congun.web.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.AddEquipment;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.model.SupplierQuote;
import com.congun.web.model.User;

@Repository
public class SupplierMapperComponent {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean SupplierMapperPreprocessor(ContractorRequirement requirement) {

		if (requirement.getEquipmentCategory() != null
				&& requirement.getEquipmentName() != null) {
			System.out
					.println("Equipment Prime categories Exists!! Hence continue for mapping");

			MapSupplier supplierMapper = new MapSupplier(requirement);
			supplierMapper.setDaemon(true);
			supplierMapper.start();
			return true;
		}

		return false;
	}

	class MapSupplier extends Thread {
		private boolean running = true;
		private boolean quit = false;
		

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

		 }
		}

	}

	public void getSupplierDetails(ContractorRequirement requirement) {

		Set<Long> mappedSupplierIds = new HashSet<Long>();
		int supplierScore;

		System.out.println("Entered Requirements Mapper");
		Criteria criteria = getSession().createCriteria(AddEquipment.class);
		@SuppressWarnings("unchecked")
		List<AddEquipment> machinesList = (ArrayList<AddEquipment>) criteria
				.add(Restrictions.eq("equipmentCategory",
						requirement.getEquipmentCategory()))
				.add(Restrictions.eq("equipment",
						requirement.getEquipmentName())).list();

		for (AddEquipment equipment : machinesList) {
			supplierScore = 0;

			if (requirement.getManufacturer() != null
					|| requirement.getManufacturer().length > 0) {
				for (String manufacturer : requirement.getManufacturer()) {
					if (equipment.getManufacturer().equals(manufacturer)) {
						// supplierScore ++;
						mappedSupplierIds.add(equipment.getSupplierId());
					}
				}
			}

			if (requirement.getSpecificationModel() != null) {
				if (equipment.getModel().equals(
						requirement.getSpecificationModel())) {
					// supplierScore ++;
					mappedSupplierIds.add(equipment.getSupplierId());
				}
			}

			if (requirement.getEquipmentManufactureYear() != 0) {

				if (equipment.getYearOfManufacturing() == requirement
						.getEquipmentManufactureYear())

					mappedSupplierIds.add(equipment.supplierId);

			}

			if (requirement.getSpecificationCapacity() != null) {
				if (equipment.getCapacity().equals(
						requirement.getSpecificationCapacity())) {
					mappedSupplierIds.add(equipment.supplierId);
				}
			}

			if ((requirement.getManufacturer() == null || requirement
					.getManufacturer().length == 0)
					&& requirement.getSpecificationModel() == null
					&& requirement.getEquipmentManufactureYear() == 0
					&& requirement.getSpecificationCapacity() == null) {
				
				mappedSupplierIds.add(equipment.getSupplierId());

			}
		}
		
		if(mappedSupplierIds.size() > 0){
		sendNotifications(mappedSupplierIds);
		}else
		{
			System.out.println("We don't have the Suppliers as per customer Requirement");
		}
	}
	
	public void sendNotifications(Set mappedSupplierIds){
		
		Iterator itr = mappedSupplierIds.iterator();
		while(itr.hasNext()){
			Long supplierId =  (Long)itr.next();
			User user = (User)getSession().createCriteria(User.class).add(Restrictions.eq("userId", supplierId.longValue())).list().get(0);
			System.out.println("User Details: ");
			System.out.println("UserId: "+user.getUserId());
			System.out.println("Name: "+user.getFirstname() + " "+user.getLastname());
			System.out.println("EmailId: "+user.getUsername());
			System.out.println("Mobile: "+user.getMobileNumber());
			System.out.println("\n");
		}
		
	}
}

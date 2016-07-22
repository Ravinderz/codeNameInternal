package com.congun.web.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplierquotes")
public class SupplierQuote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="quoteId")
	public long quoteId;
	
	@Column(name = "machineRental")
	public long machineRental;
	
	@Column(name = "perBasis")
	public String perBasis;
	
	@Column(name = "make")
	public String make;
	
	@Column(name = "model")
	public String model;
	
	@Column(name = "manufacturedyear")
	public String manufacturedyear;
	
	@Column(name = "manufacturer")
	public String manufacturer;
	
	@Column(name = "capacity")
	public int capacity;
	
	@Column(name = "driverfare")
	public long driverfare;
	
	@Column(name = "driverAvailabile")
	public boolean driverAvailable;
	
	@Column(name = "helperAvailable")
	public boolean helperAvailable;
	
	@Column(name = "maintenance")
	public long maintenance;
	
	@Column(name = "queries")
	public String queries;
	
	@Column(name = "quotePostedById")
	public long quotePostedById;
	
	@Column(name = "quotePostedByName")
	public String quotePostedByName;
		
	@Column(name = "createdTime")
	public Timestamp createdTime;
	
	@Column(name = "LastupdatedTime")
	public Timestamp updatedTime;
	
	@Column(name = "requirementId")
	public long requirementId;
	
	@Column(name = "activeFlag")
	public int activeFlag;
	
	public long getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}
	public long getQuotePostedById() {
		return quotePostedById;
	}
	public void setQuotePostedById(long quotePostedById) {
		this.quotePostedById = quotePostedById;
	}
	public String getQuotePostedByName() {
		return quotePostedByName;
	}
	public void setQuotePostedByName(String quotePostedByName) {
		this.quotePostedByName = quotePostedByName;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	public long getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(long requirementId) {
		this.requirementId = requirementId;
	}

	public long getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(long maintenance) {
		this.maintenance = maintenance;
	}
	public String getQueries() {
		return queries;
	}
	public void setQueries(String queries) {
		this.queries = queries;
	}
	
	public int getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	public long getMachineRental() {
		return machineRental;
	}
	public void setMachineRental(long machineRental) {
		this.machineRental = machineRental;
	}
	public String getPerBasis() {
		return perBasis;
	}
	public void setPerBasis(String perBasis) {
		this.perBasis = perBasis;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getManufacturedyear() {
		return manufacturedyear;
	}
	public void setManufacturedyear(String manufacturedyear) {
		this.manufacturedyear = manufacturedyear;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public long getDriverfare() {
		return driverfare;
	}
	public void setDriverfare(long driverfare) {
		this.driverfare = driverfare;
	}
	public boolean isHelperAvailable() {
		return helperAvailable;
	}
	public void setHelperAvailable(boolean helperAvailable) {
		this.helperAvailable = helperAvailable;
	}
	public void setDriverAvailable(boolean driverAvailable) {
		this.driverAvailable = driverAvailable;
	}
	

}

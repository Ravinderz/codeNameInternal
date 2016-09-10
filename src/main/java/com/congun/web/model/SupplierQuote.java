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
	
	@Column(name = "equipmentCategory")
	public String equipmentCategory;
	
	@Column(name = "equipmentName")
	public String equipmentName;
	
	@Column(name = "quantity")
	public int quantity;
	
	@Column(name = "machineRentalCharges")
	public long machineRentalCharges;
	
	@Column(name = "model")
	public String model;
	
	@Column(name = "manufacturedyear")
	public String manufacturedyear;
	
	@Column(name = "manufacturer")
	public String[] manufacturer;
	
	@Column(name = "capacity")
	public int capacity;
	
		@Column(name = "driverfare")
	public long driverfare;
	
	@Column(name = "driverAvailabile")
	public boolean driverAvailable;
	
	@Column(name = "helperAvailable")
	public boolean helperAvailable;
	
	@Column(name = "maintenanceCharges")
	public long maintenanceCharges;
	
	@Column(name = "queries")
	public String queries;
	
	@Column(name = "transportation")
	public String transportation;
	
	@Column(name = "quotePostedById")
	public long quotePostedById;
	
	@Column(name = "quoteStatus")
	public String quoteStatus;
	
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
	
	@Column(name = "mobileNumber")
	public String mobileNumber;
	
	@Column(name = "finalQuotePrice")
	public double finalQuotePrice;
	
	@Column(name = "hiringChargesType")
	public String hiringChargesType;

	@Column(name = "requirementtitle")
	public String reqtitle;
	
	@Column(name = "reqduration")
	public String reqduration;

	public String getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}

	public long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}

	public String getEquipmentCategory() {
		return equipmentCategory;
	}

	public void setEquipmentCategory(String equipmentCategory) {
		this.equipmentCategory = equipmentCategory;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
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

	public String[] getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String[] manufacturer) {
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

	public boolean isDriverAvailable() {
		return driverAvailable;
	}

	public void setDriverAvailable(boolean driverAvailable) {
		this.driverAvailable = driverAvailable;
	}

	public boolean isHelperAvailable() {
		return helperAvailable;
	}

	public void setHelperAvailable(boolean helperAvailable) {
		this.helperAvailable = helperAvailable;
	}

	public String getQueries() {
		return queries;
	}

	public void setQueries(String queries) {
		this.queries = queries;
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

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public double getFinalQuotePrice() {
		return finalQuotePrice;
	}

	public void setFinalQuotePrice(double finalQuotePrice) {
		this.finalQuotePrice = finalQuotePrice;
	}

	public String getHiringChargesType() {
		return hiringChargesType;
	}

	public void setHiringChargesType(String hiringChargesType) {
		this.hiringChargesType = hiringChargesType;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getMachineRentalCharges() {
		return machineRentalCharges;
	}

	public void setMachineRentalCharges(long machineRentalCharges) {
		this.machineRentalCharges = machineRentalCharges;
	}

	public long getMaintenanceCharges() {
		return maintenanceCharges;
	}

	public void setMaintenanceCharges(long maintenanceCharges) {
		this.maintenanceCharges = maintenanceCharges;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public String getReqtitle() {
		return reqtitle;
	}

	public void setReqtitle(String reqtitle) {
		this.reqtitle = reqtitle;
	}

	public String getReqduration() {
		return reqduration;
	}

	public void setReqduration(String reqduration) {
		this.reqduration = reqduration;
	}

	

}

package com.congun.web.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ContractorRequirement")
public class ContractorRequirement {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 public long requirementId;
 public long contractorId;
 public String title;
 public String contractorName;
 public String equipmentCategory;
 public String equipmentName;
 public String specificationCapacity;
 public String[] manufacturer;
 public String manufacturedyear;
 public String startDate;
 public String endDate;
 public int quantity;
 public int duration;
 public String hiringChargesType;
 public String workLocation;
 public String transportation;
 public String driverNeeded;
 public String dieselCharges;
 public String pincode;
 public String mobile;
 public String reqStatus;
 public String workDescription;

 public Timestamp createdTime;
 public Timestamp updatedTime;
 public int activeFlag;
 public int noofquotes;
 
 
public long getRequirementId() {
	return requirementId;
}
public void setRequirementId(long requirementId) {
	this.requirementId = requirementId;
}
public long getContractorId() {
	return contractorId;
}
public void setContractorId(long contractorId) {
	this.contractorId = contractorId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContractorName() {
	return contractorName;
}
public void setContractorName(String contractorName) {
	this.contractorName = contractorName;
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
public String getSpecificationCapacity() {
	return specificationCapacity;
}
public void setSpecificationCapacity(String specificationCapacity) {
	this.specificationCapacity = specificationCapacity;
}
public String[] getManufacturer() {
	return manufacturer;
}
public void setManufacturer(String[] manufacturer) {
	this.manufacturer = manufacturer;
}
public String getManufacturedyear() {
	return manufacturedyear;
}
public void setManufacturedyear(String manufacturedyear) {
	this.manufacturedyear = manufacturedyear;
}

public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public String getHiringChargesType() {
	return hiringChargesType;
}
public void setHiringChargesType(String hiringChargesType) {
	this.hiringChargesType = hiringChargesType;
}
public String getWorkLocation() {
	return workLocation;
}
public void setWorkLocation(String workLocation) {
	this.workLocation = workLocation;
}
public String getTransportation() {
	return transportation;
}
public void setTransportation(String transportation) {
	this.transportation = transportation;
}
public String getDriverNeeded() {
	return driverNeeded;
}
public void setDriverNeeded(String driverNeeded) {
	this.driverNeeded = driverNeeded;
}
public String getDieselCharges() {
	return dieselCharges;
}
public void setDieselCharges(String dieselCharges) {
	this.dieselCharges = dieselCharges;
}
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getReqStatus() {
	return reqStatus;
}
public void setReqStatus(String reqStatus) {
	this.reqStatus = reqStatus;
}
public String getWorkDescription() {
	return workDescription;
}
public void setWorkDescription(String workDescription) {
	this.workDescription = workDescription;
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
public int getActiveFlag() {
	return activeFlag;
}
public void setActiveFlag(int activeFlag) {
	this.activeFlag = activeFlag;
}
public int getNoofquotes() {
	return noofquotes;
}
public void setNoofquotes(int noofquotes) {
	this.noofquotes = noofquotes;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
 
}

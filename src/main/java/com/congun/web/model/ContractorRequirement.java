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
 public String contractorName;
 public String equipmentCategory;
 public String equipmentName;
 public String specificationCapacity;
 public String[] manufacturer;
 public String specificationModel;
 public int quantity;
 public int duration;
 public Date startDate;
 public int equipmentManufactureYear;
 public String hiringChargesType;
 public String workLocation;
 public String transportation;
 public String driverNeeded;
 public String helperNeeded;
 public String dieselCharges;
 public String workDescription;
 public String workAreaImages;
 public Timestamp createdTime;
 public Timestamp updatedTime;
 public int activeFlag;
 
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
public String getSpecificationModel() {
	return specificationModel;
}
public void setSpecificationModel(String specificationModel) {
	this.specificationModel = specificationModel;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public int getEquipmentManufactureYear() {
	return equipmentManufactureYear;
}
public void setEquipmentManufactureYear(int equipmentManufactureYear) {
	this.equipmentManufactureYear = equipmentManufactureYear;
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
public String getHelperNeeded() {
	return helperNeeded;
}
public void setHelperNeeded(String helperNeeded) {
	this.helperNeeded = helperNeeded;
}
public String getDieselCharges() {
	return dieselCharges;
}
public void setDieselCharges(String dieselCharges) {
	this.dieselCharges = dieselCharges;
}
public String getWorkDescription() {
	return workDescription;
}
public void setWorkDescription(String workDescription) {
	this.workDescription = workDescription;
}
public String getWorkAreaImages() {
	return workAreaImages;
}
public void setWorkAreaImages(String workAreaImages) {
	this.workAreaImages = workAreaImages;
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


}

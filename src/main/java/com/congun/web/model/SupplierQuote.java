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
	
	@Column(name = "driverAvailabile")
	public boolean driverAvailable;
	
	@Column(name = "queries")
	public String queries;
	
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

	@Column(name = "requirementtitle")
	public String reqtitle;
	
	@Column(name = "reqduration")
	public String reqduration;
	
	@Column(name = "reqlocation")
	public String reqlocation;
	
	@Column(name = "hoursPerMonth")
	public String hoursPerMonth;
	
	@Column(name = "advance")
	public String advance;

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

	public boolean isDriverAvailable() {
		return driverAvailable;
	}

	public void setDriverAvailable(boolean driverAvailable) {
		this.driverAvailable = driverAvailable;
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

	public String getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
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

	public String getReqlocation() {
		return reqlocation;
	}

	public void setReqlocation(String reqlocation) {
		this.reqlocation = reqlocation;
	}

	public String getHoursPerMonth() {
		return hoursPerMonth;
	}

	public void setHoursPerMonth(String hoursPerMonth) {
		this.hoursPerMonth = hoursPerMonth;
	}

	public String getAdvance() {
		return advance;
	}

	public void setAdvance(String advance) {
		this.advance = advance;
	}
	
}


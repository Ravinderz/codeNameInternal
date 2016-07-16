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
	
	@Column(name = "rentalCharge")
	public long rentalCharge;
	
	@Column(name = "noOfWorkingHours")
	public long noOfWorkingHours;
	
	@Column(name = "driverNeeded")
	public boolean driverNeeded;
	
	@Column(name = "helperNeeded")
	public boolean helperNeeded;
	
	@Column(name = "repairCharges")
	public long repairCharges;
	
	@Column(name = "queries")
	public String queries;
	
	@Column(name = "quotePostedById")
	public int quotePostedById;
	
	@Column(name = "quotePostedByName")
	public String quotePostedByName;
		
	@Column(name = "createdTime")
	public Timestamp createdTime;
	
	@Column(name = "LastupdatedTime")
	public Timestamp updatedTime;
	
	@Column(name = "submittedforReq")
	public int submittedforReq;
	
		public long getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}
	public long getRentalCharge() {
		return rentalCharge;
	}
	public void setRentalCharge(long rentalCharge) {
		this.rentalCharge = rentalCharge;
	}
	public int getQuotePostedById() {
		return quotePostedById;
	}
	public void setQuotePostedById(int quotePostedById) {
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
	public int getSubmittedforReq() {
		return submittedforReq;
	}
	public void setSubmittedforReq(int submittedforReq) {
		this.submittedforReq = submittedforReq;
	}
	
	
	public long getNoOfWorkingHours() {
		return noOfWorkingHours;
	}
	public void setNoOfWorkingHours(long noOfWorkingHours) {
		this.noOfWorkingHours = noOfWorkingHours;
	}

	public long getRepairCharges() {
		return repairCharges;
	}
	public void setRepairCharges(long repairCharges) {
		this.repairCharges = repairCharges;
	}
	public String getQueries() {
		return queries;
	}
	public void setQueries(String queries) {
		this.queries = queries;
	}
	public boolean isDriverNeeded() {
		return driverNeeded;
	}
	public void setDriverNeeded(boolean driverNeeded) {
		this.driverNeeded = driverNeeded;
	}
	public boolean isHelperNeeded() {
		return helperNeeded;
	}
	public void setHelperNeeded(boolean helperNeeded) {
		this.helperNeeded = helperNeeded;
	}
	
	
	public long calculateQuotation(){
	
		return noOfWorkingHours*rentalCharge;
	}
	
	public SupplierQuote compareObjects(SupplierQuote dBObj)
	{
		if(this.getRentalCharge()!=0){
			dBObj.setRentalCharge(this.getRentalCharge());
		}
		
		if(this.getNoOfWorkingHours() !=0){
			dBObj.setNoOfWorkingHours(this.getNoOfWorkingHours());
		}
		
		//if(this.isDriverNeeded())
		
		if(this.getNoOfWorkingHours()!=0)
		{
			dBObj.setNoOfWorkingHours(this.getNoOfWorkingHours());
		}
		
		
		
		
		return dBObj;
		
		
	}
	

}

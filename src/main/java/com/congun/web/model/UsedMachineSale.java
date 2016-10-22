package com.congun.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="UsedMachineSale")
public class UsedMachineSale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="postId")
	public long postId;
	
	@Column(name="userId")
	public long userId;
	
	@Column(name="userName")
	public String userName;
	
	@Column(name="contactNo")
	public String contactNo;
	
	@Column(name="emailId")
	public String emailId;
	
	@Column(name="category")
	public String category;
	
	@Column(name="equipmentName")
	public String equipmentName;
	
	@Column(name="manufacturer")
	public String manufacturer;
	
	@Column(name="yearOfManufacturer")
	public String yearOfManufacturer;
	
	@Column(name="price")
	public String price;
	
	@Column(name="location")
	public String location;
	
	@Column(name="meterReading")
	public String meterReading;
	
	@Column(name="postedTime")
	public Date postedTime;
	
	@Column(name="updatedTime")
	public Date updatedTime;
	
	@Column(name="description")
	public String description;
	
	@Column(name="noOfLikes")
	public String noOfLikes;
	
	@Column(name="noOfViews")
	public String noOfViews;
	
	@Column(name="interestedPplCount")
	public int interestedPplCount;
	
	
	@Column(name="queries")
	public String queries;
	
	@Column(name="activeFlag")
	public int activeFlag;
	
	public int getInterestedPplCount() {
		return interestedPplCount;
	}

	public void setInterestedPplCount(int interestedPplCount) {
		this.interestedPplCount = interestedPplCount;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getYearOfManufacturer() {
		return yearOfManufacturer;
	}

	public void setYearOfManufacturer(String yearOfManufacturer) {
		this.yearOfManufacturer = yearOfManufacturer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(String meterReading) {
		this.meterReading = meterReading;
	}

	public Date getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNoOfLikes() {
		return noOfLikes;
	}

	public void setNoOfLikes(String noOfLikes) {
		this.noOfLikes = noOfLikes;
	}

	public String getNoOfViews() {
		return noOfViews;
	}

	public void setNoOfViews(String noOfViews) {
		this.noOfViews = noOfViews;
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
	
	
	
}

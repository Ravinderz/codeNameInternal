package com.congun.web.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userId")
	public long userId;
	
    @Column(name = "firstname")
	public String firstname;
    
    @Column(name = "lastname")
	public String lastname;
		
	@Column(name = "username")
	public String username;
	
	@Column(name = "password")
	public String password;
	
	@Column(name = "mobileNumber")
	public String mobileNumber;

	@Column(name = "location")
	public String location;
	
	@Column(name = "constitution")
	public String constitution;
	
	@Column(name = "website")
	public String website;
	
	@Column(name = "company_individual_profile")
	public String company_individual_profile;
	
	@Column(name = "role")
	public String role;
	
	@Column(name = "activeFlag")
	public int activeFlag;
	
	@Column(name = "createdtime")
	public Timestamp createdtime;
	
	@Column(name = "updatedtime")
	public Timestamp updatedtime;

	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCompany_individual_profile() {
		return company_individual_profile;
	}
	public void setCompany_individual_profile(String company_individual_profile) {
		this.company_individual_profile = company_individual_profile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Timestamp getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}
	public Timestamp getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(Timestamp updatedtime) {
		this.updatedtime = updatedtime;
	}

	

}

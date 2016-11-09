package com.congun.web.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usedmachinemapping")
public class UsedMachineMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public long id;

	@Column(name = "userId")
	public long userId;

	@Column(name = "firstname")
	public String firstname;

	@Column(name = "lastname")
	public String lastname;

	@Column(name = "username")
	public String username;

	@Column(name = "mobileNumber")
	public String mobileNumber;

	@Column(name = "location")
	public String location;

	@Column(name = "activeFlag")
	public int activeFlag;

	@Column(name = "createdtime")
	public Timestamp createdtime;

	@Column(name = "updatedtime")
	public Timestamp updatedtime;

	@Column(name = "postId")
	public long postId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

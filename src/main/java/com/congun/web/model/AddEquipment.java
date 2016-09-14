package com.congun.web.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addEquipment")
public class AddEquipment {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
public int equipmentId; 
public long supplierId;
public String supplierName;
public String equipmentCategory;
public String equipment;
public String manufacturer;
public int yearOfManufacturing;
public String model;
public String capacity;
public int quantity;
public Timestamp createdTime;
public Timestamp updatedTime;

public String getEquipment() {
	return equipment;
}
public void setEquipment(String equipment) {
	this.equipment = equipment;
}
public int getEquipmentId() {
	return equipmentId;
}
public void setEquipmentId(int equipmentId) {
	this.equipmentId = equipmentId;
}
public long getSupplierId() {
	return supplierId;
}
public void setSupplierId(long supplierId) {
	this.supplierId = supplierId;
}
public String getSupplierName() {
	return supplierName;
}
public void setSupplierName(String supplierName) {
	this.supplierName = supplierName;
}
public String getEquipmentCategory() {
	return equipmentCategory;
}
public void setEquipmentCategory(String equipmentCategory) {
	this.equipmentCategory = equipmentCategory;
}
public String getManufacturer() {
	return manufacturer;
}
public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
}
public int getYearOfManufacturing() {
	return yearOfManufacturing;
}
public void setYearOfManufacturing(int yearOfManufacturing) {
	this.yearOfManufacturing = yearOfManufacturing;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getCapacity() {
	return capacity;
}
public void setCapacity(String capacity) {
	this.capacity = capacity;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
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
	
	

}

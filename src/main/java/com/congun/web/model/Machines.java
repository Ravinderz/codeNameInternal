package com.congun.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "machines")
public class Machines {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	@Column(name="category")
	public String category;
	
	@Column(name="equipment")
	public String equipment;
	
	@Column(name="model",unique=true)
	public String model;
	
	@Column(name="make")
	public String make;
	
	@Column(name="capacity")
	public String capacity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
}

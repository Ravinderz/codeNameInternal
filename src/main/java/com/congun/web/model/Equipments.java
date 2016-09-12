package com.congun.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipments")
public class Equipments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int equipmentId;
	public String equipmentCategory;
	public String equipmentsPath;
	
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentCategory() {
		return equipmentCategory;
	}
	public void setEquipmentCategory(String equipmentCategory) {
		this.equipmentCategory = equipmentCategory;
	}
	public String getEquipmentsPath() {
		return equipmentsPath;
	}
	public void setEquipmentsPath(String equipmentsPath) {
		this.equipmentsPath = equipmentsPath;
	}


}

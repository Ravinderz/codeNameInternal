package com.congun.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dropDownMaster")
public class DropDownMaster {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public int equipmentId;
		public String equipmentCategory;
		public String equipmentName;
		
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
		public String getEquipmentName() {
			return equipmentName;
		}
		public void setEquipmentName(String equipmentName) {
			this.equipmentName = equipmentName;
		}

}

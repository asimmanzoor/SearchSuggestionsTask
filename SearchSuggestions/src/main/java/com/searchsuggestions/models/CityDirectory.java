package com.searchsuggestions.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDirectory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "circle_name")
	private String circleName;

	@Column(name = "region_name")
	private String regionName;

	@Column(name = "division_name")
	private String divisionName;

	@Column(name = "office_name")
	private String officeName;

	@Column(name = "pin_code")
	private String pinCode;

	@Column(name = "office_type")
	private String officeType;

	@Column(name = "delivery")
	private String delivery;

	@Column(name = "district")
	private String district;

	@Column(name = "state_name")
	private String stateName;

}

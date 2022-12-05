package com.kodlamaio.filterService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarFilter {

	@Id
	private String id;
	
	private String carId;

	private double carDailyPrice;

	private int carModelYear;

	private String carPlate;

	private String carModelId;

	private String carModelName;

	private String carBrandId;

	private String carBrandName;
	
	private String carState;
}

package com.kodlamaio.filterService.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarFilter {

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

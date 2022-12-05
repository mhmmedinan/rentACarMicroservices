package com.kodlamaiorentalService.business.responses.rentals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentalResponse {

	private String id;
	private double carDailyPrice;
	private int carModelYear;
	private String carPlate;
	private int carState;
	private String carModelName;
	private String carModelBrandName;
	
}

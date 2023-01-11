package com.kodlamaio.rentalService.business.responses.get;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentalResponse {

	private String id;
	private LocalDate dateStarted;
	private String carId;
	private int rentedForDays;
	private double dailyPrice;
	private double balance;
	
}

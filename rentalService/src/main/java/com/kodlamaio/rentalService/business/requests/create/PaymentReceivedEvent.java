package com.kodlamaio.rentalService.business.requests.create;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReceivedEvent {

	private String customerId;
	
	private String carId;


	private String modelName;

	private String brandName;

	private int modelYear;

	private double dailyPrice;

	private double totalPrice;

	private int rentedForDays;

	private LocalDate rentedDate;
	
	private String customerUserName;
	
    private String customerFirstName;
    
    private String customerLastName;
    
    private String customerEmail;
}
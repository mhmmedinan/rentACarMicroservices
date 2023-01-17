package com.kodlamaio.paymentService.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentResponse {

	private String id;
	private String cardName;
	private String cardNumber;
	private String expirationDate;
	private String cvv;
	private double balance;
}

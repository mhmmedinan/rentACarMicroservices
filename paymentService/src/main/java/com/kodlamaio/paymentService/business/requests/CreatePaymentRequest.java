package com.kodlamaio.paymentService.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

	private String cardName;
	private String cardNumber;
	private String expirationDate;
	private String cvv;
	private double balance;
}

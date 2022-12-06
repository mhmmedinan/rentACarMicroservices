package com.kodlamaio.paymentService.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentResponse {

	private String cardName;
	private String cardNumber;
	private String cvv;
	private double price;
}

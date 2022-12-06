package com.kodlamaio.paymentService.business.abstracts;

import com.kodlamaio.paymentService.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponse;
import com.kodlamaio.paymentService.entities.Payment;

public interface PaymentService {

	CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest);
	void paymentReceived(String cardNumber,String cardName,String cvv,double price);
	Payment getByCardNumber(String cardNumber);
}

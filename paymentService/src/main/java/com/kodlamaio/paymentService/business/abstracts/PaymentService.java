package com.kodlamaio.paymentService.business.abstracts;

import com.kodlamaio.common.utilities.dto.CustomerRequest;
import com.kodlamaio.paymentService.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponse;

public interface PaymentService {

	CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest,CustomerRequest customerRequest);
	void paymentReceived(String cardNumber,String cardName,String cvv,double price);
	
}

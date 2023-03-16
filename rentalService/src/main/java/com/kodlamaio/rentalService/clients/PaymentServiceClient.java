package com.kodlamaio.rentalService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodlamaio.rentalService.business.requests.create.CreatePaymentRequest;

@FeignClient(name = "payment-service")
public interface PaymentServiceClient {

	@RequestMapping(method= RequestMethod.POST,value="/api/payments/received")
	void paymentReceived(@RequestBody CreatePaymentRequest request);
}

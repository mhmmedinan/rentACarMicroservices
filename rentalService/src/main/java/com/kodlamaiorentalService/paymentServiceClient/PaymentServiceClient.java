package com.kodlamaiorentalService.paymentServiceClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Headers;

@FeignClient(value = "payment",url="http://paymentservice.api:9011/")
public interface PaymentServiceClient {
	
	@RequestMapping(method= RequestMethod.POST ,value = "payment-service/api/payments/received") 
	@Headers(value="Content-Type: application/json")
	void paymentReceived(@RequestParam String cardNumber, @RequestParam String cardName,
            @RequestParam String cvv, @RequestParam String expirationDate, @RequestParam double price);

	
}

package com.kodlamaio.rentalService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service")
public interface PaymentServiceClient {

	@RequestMapping(method= RequestMethod.POST,value="/api/payments/received")
	void paymentReceived(@RequestParam String cardNumber, @RequestParam String cardName,
            @RequestParam String cvv, @RequestParam String expirationDate, @RequestParam double price);
}

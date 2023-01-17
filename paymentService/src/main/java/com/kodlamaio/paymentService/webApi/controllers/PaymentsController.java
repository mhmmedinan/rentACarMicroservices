package com.kodlamaio.paymentService.webApi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.paymentService.business.abstracts.PaymentService;
import com.kodlamaio.paymentService.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentsController {

	private PaymentService paymentService;
	
	@PostMapping("add")
	public CreatePaymentResponse add(@RequestBody CreatePaymentRequest createPaymentRequest) {
		return paymentService.add(createPaymentRequest);
	}
	@PostMapping("/received")
	public void paymentReceived(@RequestParam String cardNumber, @RequestParam String cardName,
            @RequestParam String cvv, @RequestParam double price) {
		
		paymentService.paymentReceived(cardNumber,cardName,cvv,price);
		
	}
	
}

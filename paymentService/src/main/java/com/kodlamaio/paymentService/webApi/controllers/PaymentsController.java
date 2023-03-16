package com.kodlamaio.paymentService.webApi.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.dto.CustomerRequest;
import com.kodlamaio.common.utilities.security.ParseJwtToCustomerRequest;
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
	public CreatePaymentResponse add(@RequestBody CreatePaymentRequest createPaymentRequest,@AuthenticationPrincipal Jwt jwt) {
		CustomerRequest customerRequest = ParseJwtToCustomerRequest.getCustomerInformation(jwt);
		return paymentService.add(createPaymentRequest,customerRequest);
	}
	@PostMapping("/received")
	public void paymentReceived(@RequestBody CreatePaymentRequest request) {
		paymentService.paymentReceived(request);	
	}
	
}

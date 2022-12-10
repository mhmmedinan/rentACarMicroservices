package com.kodlamaiorentalService.webApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaiorentalService.business.abstracts.RentalService;
import com.kodlamaiorentalService.business.requests.rentals.CreatePaymentRequest;
import com.kodlamaiorentalService.business.requests.rentals.CreateRentalRequest;
import com.kodlamaiorentalService.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaiorentalService.business.responses.rentals.CreateRentalResponse;
import com.kodlamaiorentalService.business.responses.rentals.GetAllRentalResponse;
import com.kodlamaiorentalService.business.responses.rentals.UpdateRentalResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalsController {

	private RentalService rentalService;
	

	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		DataResult<List<GetAllRentalResponse>> result = rentalService.getAll();
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);

		}
		return ResponseEntity.badRequest().body(result);
	}

	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody CreateRentalRequest createRentalRequest,@RequestParam String cardNumber, @RequestParam String cardName,
            @RequestParam String cvv) {
		CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
		createPaymentRequest.setCardName(cardName);
		createPaymentRequest.setCardNumber(cardNumber);
		createPaymentRequest.setCvv(cvv);
		DataResult<CreateRentalResponse> result = rentalService.add(createRentalRequest,createPaymentRequest);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);

		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody UpdateRentalRequest updateRentalRequest) {
		DataResult<UpdateRentalResponse> result = rentalService.update(updateRentalRequest);

		if (result.isSuccess()) {
			return ResponseEntity.ok(result);

		}
		return ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping("/getById/{rentalId}")
	public ResponseEntity<?> getById(@PathVariable String rentalId) {
		DataResult<GetAllRentalResponse> result = rentalService.getById(rentalId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
	
	
}

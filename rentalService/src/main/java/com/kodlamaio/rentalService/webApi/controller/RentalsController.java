package com.kodlamaio.rentalService.webApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.get.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.responses.update.UpdateRentalResponse;

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
	public ResponseEntity<?> add(@RequestBody CreateRentalRequest createRentalRequest) {
//		CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
//		createPaymentRequest.setCardName(cardName);
//		createPaymentRequest.setCardNumber(cardNumber);
//		createPaymentRequest.setCvv(cvv);
		DataResult<CreateRentalResponse> result = rentalService.add(createRentalRequest);
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

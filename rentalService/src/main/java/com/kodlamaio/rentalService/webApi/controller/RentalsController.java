package com.kodlamaio.rentalService.webApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.dto.CustomerRequest;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.security.ParseJwtToCustomerRequest;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.create.CreatePaymentRequest;
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
	@PreAuthorize("hasRole('admin') or hasRole('developer') or hasRole('user')")
	public ResponseEntity<?> getAll() {
		DataResult<List<GetAllRentalResponse>> result = rentalService.getAll();
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);

		}
		return ResponseEntity.badRequest().body(result);
	}

	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody CreateRentalRequest createRentalRequest,
			@AuthenticationPrincipal Jwt jwt,@RequestParam String cardNumber, @RequestParam String cardName,
            @RequestParam String cvv) {
		CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
		createPaymentRequest.setCardName(cardName);
		createPaymentRequest.setCardNumber(cardNumber);
		createPaymentRequest.setCvv(cvv);
		CustomerRequest customerRequest = ParseJwtToCustomerRequest.getCustomerInformation(jwt);
		DataResult<CreateRentalResponse> result = rentalService.add(createRentalRequest,customerRequest,createPaymentRequest);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);

		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("update")
	@PreAuthorize("hasRole('admin') or hasRole('developer')")
	public ResponseEntity<?> update(@Valid @RequestBody UpdateRentalRequest updateRentalRequest,
			@AuthenticationPrincipal Jwt jwt) {
		CustomerRequest customerRequest = ParseJwtToCustomerRequest.getCustomerInformation(jwt);
		DataResult<UpdateRentalResponse> result = rentalService.update(updateRentalRequest,customerRequest);

		if (result.isSuccess()) {
			return ResponseEntity.ok(result);

		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping("/getById/{rentalId}")
	@PreAuthorize("hasRole('admin') or hasRole('developer') or hasRole('user')" + "|| returnObject.customerId == #jwt.subject")
	public ResponseEntity<?> getById(@PathVariable String rentalId, @AuthenticationPrincipal Jwt jwt) {
		DataResult<GetAllRentalResponse> result = rentalService.getById(rentalId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

}

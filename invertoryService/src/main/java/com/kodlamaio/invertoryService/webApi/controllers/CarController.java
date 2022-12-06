package com.kodlamaio.invertoryService.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.invertoryService.business.abstracts.CarService;
import com.kodlamaio.invertoryService.business.requests.create.CreateCarRequest;
import com.kodlamaio.invertoryService.business.requests.update.UpdateCarRequest;
import com.kodlamaio.invertoryService.business.responses.create.CreateCarResponse;
import com.kodlamaio.invertoryService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.invertoryService.business.responses.update.UpdateCarResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

	private CarService carService;

	@GetMapping("getall")
	public ResponseEntity<?> getAll() {
		DataResult<List<GetAllCarsResponse>> result = carService.getAll();
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@GetMapping("/getById/{carId}")
	public GetAllCarsResponse getById(@PathVariable String carId) {
		return carService.getById(carId);
	}

	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		DataResult<CreateCarResponse> result = carService.add(createCarRequest);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		DataResult<UpdateCarResponse> result = carService.update(updateCarRequest);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		Result result = carService.delete(id);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
		
	}

}

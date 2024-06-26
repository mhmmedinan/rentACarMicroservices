package com.kodlamaio.rentalService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kodlamaio.rentalService.business.responses.get.GetAllCarsResponse;

@FeignClient(name ="inventory-service")
public interface InventoryServiceClient {

	@GetMapping("/api/cars/getByCarId/{carId}")
	GetAllCarsResponse getByCarId(@PathVariable String carId);
}
	

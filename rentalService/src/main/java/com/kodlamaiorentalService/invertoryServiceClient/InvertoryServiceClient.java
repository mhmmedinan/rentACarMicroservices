package com.kodlamaiorentalService.invertoryServiceClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.kodlamaiorentalService.business.responses.rentals.GetAllCarsResponse;

@FeignClient(name ="localhost:9011/invertory-service",path = "/api/cars")
public interface InvertoryServiceClient {

	@GetMapping("/getById/{carId}")
	GetAllCarsResponse getByCarId(@PathVariable String carId);
}
	

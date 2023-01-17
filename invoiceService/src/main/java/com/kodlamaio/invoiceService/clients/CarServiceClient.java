package com.kodlamaio.invoiceService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kodlamaio.invoiceService.business.responses.get.GetAllCarsResponse;

@FeignClient(name="inventory-service")
public interface CarServiceClient {

	@GetMapping("/api/cars/getById/{carId}")
	GetAllCarsResponse getByCarId(@PathVariable String carId);
}

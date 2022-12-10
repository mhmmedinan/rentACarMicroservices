package com.kodlamaiorentalService.invertoryServiceClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodlamaiorentalService.business.responses.rentals.GetAllCarsResponse;

import feign.Headers;

@FeignClient(value ="car",url = "http://localhost:9011/")
public interface InvertoryServiceClient {

	@RequestMapping(method =RequestMethod.GET,value ="invertory-service/api/cars/getById/{carId}")
	@Headers(value = "Content-Type: application/json")
	GetAllCarsResponse getByCarId(@PathVariable String carId);
}
	
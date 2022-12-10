package com.kodlamaio.invoiceService.carServiceClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodlamaio.invoiceService.business.responses.GetAllCarsResponse;

import feign.Headers;

@FeignClient(value ="invertory-service",url = "http://localhost:9011/")
public interface CarServiceClient {

	@RequestMapping(method =RequestMethod.GET,value ="invertory-service/api/cars/getById/{carId}")
	@Headers(value = "Content-Type: application/json")
	GetAllCarsResponse getByCarId(@PathVariable String carId);
}

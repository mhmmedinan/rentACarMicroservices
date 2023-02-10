package com.kodlamaio.filterService.webApi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.filterService.business.abstracts.CarFilterService;
import com.kodlamaio.filterService.business.response.GetAllCarFilter;
import com.kodlamaio.filterService.entities.CarFilter;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/carfilters")
@AllArgsConstructor
public class CarFilterController {

	private CarFilterService carFilterService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(){
		DataResult<List<GetAllCarFilter>> result = carFilterService.getAll();
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping("/getbrandname")
	public ResponseEntity<?> getBrandName(@RequestParam String brandName){
		DataResult<List<GetAllCarFilter>> result = carFilterService.getBrandName(brandName);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);

	}
	
	@GetMapping("/getmodelname")
	public ResponseEntity<?> getModelName(@RequestParam String modelName){
		DataResult<List<GetAllCarFilter>> result = carFilterService.getModelName(modelName);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);

	}
	
	@GetMapping("/getplate")
	public ResponseEntity<?> getPlate(@RequestParam String plate){
		DataResult<List<GetAllCarFilter>> result = carFilterService.getPlate(plate);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);

	}
	
	
	@GetMapping("/getmodelyear")
	public ResponseEntity<?> getModelYear(@RequestParam int modelYear){
		DataResult<List<GetAllCarFilter>> result = carFilterService.getModelYear(modelYear);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);

	}
	
	
	@GetMapping("/getmodelid")
	public ResponseEntity<?> getByModelId(@RequestParam String modelId){
		DataResult<List<CarFilter>> result = carFilterService.getByModelId(modelId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);

	}
	
	@GetMapping("/getbrandid")
	public ResponseEntity<?> getByBrandId(@RequestParam String brandId){
		DataResult<List<CarFilter>> result = carFilterService.getByBrandId(brandId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);

	}
	
	
	@GetMapping("/getcarid")
	public DataResult<CarFilter> getByCarId(@RequestParam String carId){
		return carFilterService.getByCarId(carId);
	}
	
}

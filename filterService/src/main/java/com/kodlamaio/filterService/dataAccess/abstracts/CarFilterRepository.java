package com.kodlamaio.filterService.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.filterService.entities.CarFilter;

public interface CarFilterRepository extends MongoRepository<CarFilter, String> {

	List<CarFilter> getByCarId(String carId);

	List<CarFilter> getByCarBrandId(String brandId);

	List<CarFilter> getByCarModelId(String modelId);
	
	CarFilter deleteByCarId(String carId);
	CarFilter deleteAllByCarBrandId(String brandId);
	CarFilter deleteAllByCarModelId(String modelId);
}

package com.kodlamaio.filterService.dataAccess;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.filterService.entities.CarFilter;

public interface CarFilterRepository extends MongoRepository<CarFilter, String> {

	CarFilter findByCarId(String carId);

	List<CarFilter> findByCarBrandId(String brandId);

	List<CarFilter> findByCarModelId(String modelId);

	List<CarFilter> findByCarBrandName(String carBrandName);

	List<CarFilter> findByCarModelName(String carModelName);

	List<CarFilter> findByCarModelYear(int carModelYear);

	List<CarFilter> findByCarPlate(String carPlate);

	CarFilter deleteByCarId(String carId);

	CarFilter deleteAllByCarBrandId(String brandId);

	CarFilter deleteAllByCarModelId(String modelId);

}

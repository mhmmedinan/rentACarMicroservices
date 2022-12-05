package com.kodlamaio.filterService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.filterService.entities.CarFilter;

public interface CarFilterService {

	Result add(CarFilter carFilter);
	Result update(CarFilter carFilter);
	DataResult<List<CarFilter>> getByCarId(String carId);
	DataResult<List<CarFilter>> getByModelId(String modelId);
	DataResult<List<CarFilter>> getByBrandId(String brandId);
	Result updateBrand(CarFilter carFilter);
	Result deleteCar(String carId);
	Result deleteModel(String modelId);
	Result deleteBrand(String brandId);
}

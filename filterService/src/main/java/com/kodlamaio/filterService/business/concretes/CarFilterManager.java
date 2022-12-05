package com.kodlamaio.filterService.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.common.utilities.results.SuccessResult;
import com.kodlamaio.filterService.business.abstracts.CarFilterService;
import com.kodlamaio.filterService.dataAccess.abstracts.CarFilterRepository;
import com.kodlamaio.filterService.entities.CarFilter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarFilterManager implements CarFilterService {

	private CarFilterRepository carFilterRepository;
	
	@Override
	public Result add(CarFilter carFilter) {
		carFilterRepository.save(carFilter);
		return new SuccessResult();
	}

	@Override
	public Result update(CarFilter carFilter) {
		carFilterRepository.save(carFilter);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<CarFilter>> getByCarId(String carId) {
		List<CarFilter> carFilter = carFilterRepository.getByCarId(carId);
		return new SuccessDataResult<List<CarFilter>>(carFilter);
	}

	@Override
	public DataResult<List<CarFilter>> getByModelId(String modelId) {
		List<CarFilter> carFilter = carFilterRepository.getByCarModelId(modelId);
		return new SuccessDataResult<List<CarFilter>>(carFilter);
	}

	@Override
	public DataResult<List<CarFilter>> getByBrandId(String brandId) {
		List<CarFilter> carFilter = carFilterRepository.getByCarBrandId(brandId);
		return new SuccessDataResult<List<CarFilter>>(carFilter);
	}


	@Override
	public Result updateBrand(CarFilter carFilter) {
		 getByBrandId(carFilter.getCarBrandId()).getData().
				forEach(filter->
				{
					filter.setCarBrandName(carFilter.getCarBrandName());
					carFilterRepository.save(filter);
				});
		 return new SuccessResult();
				
	}

	@Override
	public Result deleteCar(String carId) {
		carFilterRepository.deleteByCarId(carId);
		return new SuccessResult();
	}

	@Override
	public Result deleteModel(String modelId) {
		carFilterRepository.deleteAllByCarModelId(modelId);
		return new SuccessResult();
	}

	@Override
	public Result deleteBrand(String brandId) {
		carFilterRepository.deleteAllByCarBrandId(brandId);
		return new SuccessResult();
	}

}

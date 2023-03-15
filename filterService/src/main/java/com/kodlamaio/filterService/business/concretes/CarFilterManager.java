package com.kodlamaio.filterService.business.concretes;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.common.utilities.results.SuccessResult;
import com.kodlamaio.filterService.business.abstracts.CarFilterService;
import com.kodlamaio.filterService.business.response.GetAllCarFilter;
import com.kodlamaio.filterService.dataAccess.CarFilterRepository;
import com.kodlamaio.filterService.entities.CarFilter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarFilterManager implements CarFilterService {

	private CarFilterRepository carFilterRepository;
	private ModelMapperService modelMapperService;

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
	public DataResult<CarFilter> getByCarId(String carId) {
		CarFilter carFilter = carFilterRepository.findByCarId(carId);
		return new SuccessDataResult<CarFilter>(carFilter);
	}

	@Override
	public DataResult<List<CarFilter>> getByModelId(String modelId) {
		List<CarFilter> carFilter = carFilterRepository.findByCarModelId(modelId);
		return new SuccessDataResult<List<CarFilter>>(carFilter);
	}

	@Override
	public DataResult<List<CarFilter>> getByBrandId(String brandId) {
		List<CarFilter> carFilter = carFilterRepository.findByCarBrandId(brandId);
		return new SuccessDataResult<List<CarFilter>>(carFilter);
	}

	@Override
	public Result updateBrand(CarFilter carFilter) {
		getByBrandId(carFilter.getCarBrandId()).getData().forEach(filter -> {
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
	
	
	
	

	@Override
	public DataResult<List<GetAllCarFilter>> getAll() {
		List<CarFilter> carFilters = carFilterRepository.findAll();
		List<GetAllCarFilter> response = carFilters.stream()
				.map(carFilter -> modelMapperService.forResponse().map(carFilter, GetAllCarFilter.class)).toList();
		return new SuccessDataResult<List<GetAllCarFilter>>(response);
	}

	@Override
	public DataResult<List<GetAllCarFilter>> getBrandName(String carBrandName) {
		List<CarFilter> carFilters = carFilterRepository.findByCarBrandName(carBrandName);
		List<GetAllCarFilter> response = carFilters.stream()
				.map(carFilter -> modelMapperService.forResponse().map(carFilter, GetAllCarFilter.class)).toList();
		return new SuccessDataResult<List<GetAllCarFilter>>(response);
	}

	@Override
	public DataResult<List<GetAllCarFilter>> getModelName(String carModelName) {
		List<CarFilter> carFilters = carFilterRepository.findByCarModelName(carModelName);
		List<GetAllCarFilter> response = carFilters.stream()
				.map(carFilter -> modelMapperService.forResponse().map(carFilter, GetAllCarFilter.class)).toList();
		return new SuccessDataResult<List<GetAllCarFilter>>(response);
	}

	@Override
	public DataResult<List<GetAllCarFilter>> getPlate(String plate) {
		List<CarFilter> carFilters = carFilterRepository.findByCarPlate(plate);
		List<GetAllCarFilter> response = carFilters.stream()
				.map(carFilter -> modelMapperService.forResponse().map(carFilter, GetAllCarFilter.class)).toList();
		return new SuccessDataResult<List<GetAllCarFilter>>(response);
	}

	@Override
	public DataResult<List<GetAllCarFilter>> getModelYear(int modelYear) {
		List<CarFilter> carFilters = carFilterRepository.findByCarModelYear(modelYear);
		List<GetAllCarFilter> response = carFilters.stream()
				.map(carFilter -> modelMapperService.forResponse().map(carFilter, GetAllCarFilter.class)).toList();
		return new SuccessDataResult<List<GetAllCarFilter>>(response);
	}

}

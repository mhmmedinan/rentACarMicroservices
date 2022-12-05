package com.kodlamaio.filterService.business.concretes;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.results.Result;
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

}

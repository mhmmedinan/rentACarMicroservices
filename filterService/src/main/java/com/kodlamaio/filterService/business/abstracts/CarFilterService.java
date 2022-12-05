package com.kodlamaio.filterService.business.abstracts;

import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.filterService.entities.CarFilter;

public interface CarFilterService {

	Result add(CarFilter carFilter);
}

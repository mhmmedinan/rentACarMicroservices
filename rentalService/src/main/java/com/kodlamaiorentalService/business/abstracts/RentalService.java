package com.kodlamaiorentalService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaiorentalService.business.requests.rentals.CreateRentalRequest;
import com.kodlamaiorentalService.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaiorentalService.business.responses.rentals.CreateRentalResponse;
import com.kodlamaiorentalService.business.responses.rentals.GetAllRentalResponse;
import com.kodlamaiorentalService.business.responses.rentals.UpdateRentalResponse;

public interface RentalService {

	DataResult<List<GetAllRentalResponse>> getAll();
	DataResult<CreateRentalResponse> add(CreateRentalRequest createRentalRequest);
	DataResult<UpdateRentalResponse> update(UpdateRentalRequest updateRentalRequest);
}

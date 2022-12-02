package com.kodlamaiorentalService.business.abstracts;

import com.kodlamaiorentalService.business.requests.rentals.CreateRentalRequest;
import com.kodlamaiorentalService.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaiorentalService.business.responses.rentals.CreateRentalResponse;
import com.kodlamaiorentalService.business.responses.rentals.UpdateRentalResponse;

public interface RentalService {

	CreateRentalResponse add(CreateRentalRequest createRentalRequest);
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest);
}

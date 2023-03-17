package com.kodlamaio.rentalService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.dto.CustomerRequest;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.rentalService.business.requests.create.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.get.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.responses.update.UpdateRentalResponse;

public interface RentalService {

	DataResult<List<GetAllRentalResponse>> getAll();
	DataResult<GetAllRentalResponse> getById(String id);
	DataResult<CreateRentalResponse> add(CreateRentalRequest createRentalRequest,CustomerRequest customerRequest,CreatePaymentRequest createPaymentRequest);
	DataResult<UpdateRentalResponse> update(UpdateRentalRequest updateRentalRequest,CustomerRequest customerRequest);
}

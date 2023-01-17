package com.kodlamaio.rentalService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rentals.RentalCreatedEvent;
import com.kodlamaio.common.events.rentals.RentalUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.constants.Messages;
import com.kodlamaio.rentalService.business.requests.create.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.rentalService.business.responses.get.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.responses.update.UpdateRentalResponse;
import com.kodlamaio.rentalService.clients.InventoryServiceClient;
import com.kodlamaio.rentalService.clients.PaymentServiceClient;
import com.kodlamaio.rentalService.dataAccess.RentalRepository;
import com.kodlamaio.rentalService.entities.Rental;
import com.kodlamaio.rentalService.kafka.producers.RentalCreatedProducer;
import com.kodlamaio.rentalService.kafka.producers.RentalUpdatedProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private RentalCreatedProducer rentalCreatedProducer;
	private RentalUpdatedProducer rentalUpdatedProducer;
//	private PaymentReceivedProducer paymentReceivedProducer;
	private InventoryServiceClient inventoryServiceClient;
	private PaymentServiceClient paymentServiceClient;

	@Override
	public DataResult<List<GetAllRentalResponse>> getAll() {
		List<Rental> rentals = rentalRepository.findAll();
		List<GetAllRentalResponse> responses = rentals.stream()
				.map(rental -> modelMapperService.forResponse().map(rental, GetAllRentalResponse.class)).toList();
		return new SuccessDataResult<List<GetAllRentalResponse>>(responses, Messages.RentalListed);

	}

	@Override
	public DataResult<CreateRentalResponse> add(CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest) {
		checkIfCarState(createRentalRequest.getCarId());
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());
		rental.setTotalPrice(createRentalRequest.getDailyPrice() * createRentalRequest.getRentedForDays());

		paymentServiceClient.paymentReceived(createPaymentRequest.getCardNumber()
				,createPaymentRequest.getCardName(),createPaymentRequest.getCvv(),createPaymentRequest.getExpirationDate(),rental.getTotalPrice());
		rentalRepository.save(rental);

		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(createRentalRequest.getCarId());
		rentalCreatedEvent.setMessage(Messages.RentalCreated);
		this.rentalCreatedProducer.sendMessage(rentalCreatedEvent);

//		PaymentReceivedEvent paymentReceivedEvent = new PaymentReceivedEvent();
//		paymentReceivedEvent.setCarId(rental.getCarId());
//		paymentReceivedEvent.setFullName(createPaymentRequest.getCardName());
//		paymentReceivedEvent.setDailyPrice(createRentalRequest.getDailyPrice());
//		paymentReceivedEvent.setTotalPrice(rental.getTotalPrice());
//		paymentReceivedEvent.setRentedForDays(createRentalRequest.getRentedForDays());
//		paymentReceivedEvent.setRentedDate(rental.getDateStarted());
//		paymentReceivedProducer.sendMessage(paymentReceivedEvent);

		CreateRentalResponse response = modelMapperService.forResponse().map(rental, CreateRentalResponse.class);
		return new SuccessDataResult<CreateRentalResponse>(response, Messages.RentalCreated);

	}

	@Override
	public DataResult<UpdateRentalResponse> update(UpdateRentalRequest updateRentalRequest) {
		checkIfCarState(updateRentalRequest.getCarId());
		RentalUpdatedEvent rentalUpdatedEvent = new RentalUpdatedEvent();
		Rental rental = rentalRepository.findById(updateRentalRequest.getId()).get();
		rentalUpdatedEvent.setOldCarId(rental.getCarId());

		rental.setCarId(updateRentalRequest.getCarId());
		rental.setDailyPrice(updateRentalRequest.getDailyPrice());
		rental.setRentedForDays(updateRentalRequest.getRentedForDays());
		rental.setTotalPrice(updateRentalRequest.getDailyPrice() * updateRentalRequest.getRentedForDays());

		Rental updatedRental = rentalRepository.save(rental);

		rentalUpdatedEvent.setNewCarId(updatedRental.getCarId());
		rentalUpdatedEvent.setMessage("Rental Updated");
		rentalUpdatedProducer.sendMessage(rentalUpdatedEvent);

		UpdateRentalResponse response = modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);
		return new SuccessDataResult<UpdateRentalResponse>(response, Messages.RentalUpdated);
	}

	private void checkIfCarState(String carId) {

		GetAllCarsResponse allCarsResponse = inventoryServiceClient.getByCarId(carId);
		if (allCarsResponse.getState() == 2) {
			throw new BusinessException("This vehicle has been rented before. Rental status is passive!");
		}
	}

	@Override
	public DataResult<GetAllRentalResponse> getById(String id) {
		Rental rental = rentalRepository.findById(id).get();
		GetAllRentalResponse response = modelMapperService.forResponse().map(rental, GetAllRentalResponse.class);
		return new SuccessDataResult<GetAllRentalResponse>(response);
	}

}

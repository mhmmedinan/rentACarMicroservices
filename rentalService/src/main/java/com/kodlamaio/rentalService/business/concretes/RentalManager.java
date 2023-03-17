package com.kodlamaio.rentalService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rentals.PaymentReceivedEvent;
import com.kodlamaio.common.events.rentals.RentalCreatedEvent;
import com.kodlamaio.common.events.rentals.RentalUpdatedEvent;
import com.kodlamaio.common.utilities.dto.CustomerRequest;
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
import com.kodlamaio.rentalService.kafka.producers.PaymentReceivedProducer;
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
	private PaymentReceivedProducer paymentReceivedProducer;
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
	public DataResult<CreateRentalResponse> add(CreateRentalRequest createRentalRequest,
			CustomerRequest customerRequest,CreatePaymentRequest createPaymentRequest) {
		checkIfCarState(createRentalRequest.getCarId());
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());
		double totalPrice = createRentalRequest.getDailyPrice() * createRentalRequest.getRentedForDays();
		rental.setTotalPrice(totalPrice);

		paymentServiceClient.paymentReceived(createPaymentRequest.getCardNumber()
				,createPaymentRequest.getCardName(),createPaymentRequest.getCvv(),createPaymentRequest.getExpirationDate(),rental.getTotalPrice());
		setCustomer(customerRequest, rental);
		rentalRepository.save(rental);
		rentalCreatedEvent(createRentalRequest);
		paymentReceivedEvent(createRentalRequest, rental);
		CreateRentalResponse response = modelMapperService.forResponse().map(rental, CreateRentalResponse.class);
		return new SuccessDataResult<CreateRentalResponse>(response, Messages.RentalCreated);

	}

	@Override
	public DataResult<UpdateRentalResponse> update(UpdateRentalRequest updateRentalRequest,CustomerRequest customerRequest) {
		checkIfCarState(updateRentalRequest.getCarId());
		RentalUpdatedEvent rentalUpdatedEvent = new RentalUpdatedEvent();
		Rental rental = rentalRepository.findById(updateRentalRequest.getId()).get();
		rentalUpdatedEvent.setOldCarId(rental.getCarId());

		rental.setCarId(updateRentalRequest.getCarId());
		rental.setDailyPrice(updateRentalRequest.getDailyPrice());
		rental.setRentedForDays(updateRentalRequest.getRentedForDays());
		rental.setTotalPrice(updateRentalRequest.getDailyPrice() * updateRentalRequest.getRentedForDays());
		setCustomer(customerRequest, rental);
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
	
	private void rentalCreatedEvent(CreateRentalRequest createRentalRequest) {
		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(createRentalRequest.getCarId());
		rentalCreatedEvent.setMessage(Messages.RentalCreated);
		rentalCreatedProducer.sendMessage(rentalCreatedEvent);
	}
	
	private void paymentReceivedEvent(CreateRentalRequest rentalRequest,Rental rental) {
		PaymentReceivedEvent paymentReceivedEvent = new PaymentReceivedEvent();
		paymentReceivedEvent.setCarId(rental.getCarId());
		paymentReceivedEvent.setDailyPrice(rentalRequest.getDailyPrice());
		paymentReceivedEvent.setTotalPrice(rental.getTotalPrice());
		paymentReceivedEvent.setRentedForDays(rentalRequest.getRentedForDays());
		paymentReceivedEvent.setRentedDate(rental.getDateStarted());
		paymentReceivedEvent.setCustomerId(rental.getCustomerId());
        paymentReceivedEvent.setCustomerUserName(rental.getCustomerUserName());
        paymentReceivedEvent.setCustomerFirstName(rental.getCustomerFirstName());
        paymentReceivedEvent.setCustomerLastName(rental.getCustomerLastName());
        paymentReceivedEvent.setCustomerEmail(rental.getCustomerEmail());
		paymentReceivedProducer.sendMessage(paymentReceivedEvent);
	}
	
	private static void setCustomer(CustomerRequest customerRequest, Rental rental) {
        rental.setCustomerId(customerRequest.getCustomerId());
        rental.setCustomerUserName(customerRequest.getCustomerUserName());
        rental.setCustomerFirstName(customerRequest.getCustomerFirstName());
        rental.setCustomerLastName(customerRequest.getCustomerLastName());
        rental.setCustomerEmail(customerRequest.getCustomerEmail());
    }

}

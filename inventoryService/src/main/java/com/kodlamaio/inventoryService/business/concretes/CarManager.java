package com.kodlamaio.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.cars.CarCreatedEvent;
import com.kodlamaio.common.events.cars.CarDeletedEvent;
import com.kodlamaio.common.events.cars.CarUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.common.utilities.results.SuccessResult;
import com.kodlamaio.inventoryService.business.abstracts.CarService;
import com.kodlamaio.inventoryService.business.constans.Messages;
import com.kodlamaio.inventoryService.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryService.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryService.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryService.business.responses.update.UpdateCarResponse;
import com.kodlamaio.inventoryService.dataAccess.CarRepository;
import com.kodlamaio.inventoryService.entities.Car;
import com.kodlamaio.inventoryService.kafka.producers.CarCreatedProducer;
import com.kodlamaio.inventoryService.kafka.producers.CarDeletedProducer;
import com.kodlamaio.inventoryService.kafka.producers.CarUpdatedProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

	private CarRepository carRespository;
	private ModelMapperService modelMapperService;
	private CarCreatedProducer carCreatedProducer;
	private CarUpdatedProducer carUpdatedProducer;
	private CarDeletedProducer carDeletedProducer;

	@Override
	@PreAuthorize("hasRole('admin') or hasRole('developer') or hasRole('user')")
	public DataResult<List<GetAllCarsResponse>> getAll() {

		List<Car> cars = carRespository.findAll();
		List<GetAllCarsResponse> responses = cars.stream()
				.map(car -> modelMapperService.forResponse().map(car, GetAllCarsResponse.class)).toList();
		return new SuccessDataResult<List<GetAllCarsResponse>>(responses, Messages.CarListed);
	}

	@Override
	@PreAuthorize("hasRole('admin') or hasRole('developer') or hasRole('user')")
	public DataResult<CreateCarResponse> add(CreateCarRequest createCarRequest) {
		checkIfCarPlateExits(createCarRequest.getPlate());
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());
		carRespository.save(car);

		GetAllCarsResponse result = getById(car.getId());
		CarCreatedEvent carCreatedEvent = modelMapperService.forResponse().map(result, CarCreatedEvent.class);
		carCreatedEvent.setMessage(Messages.CarAdded);
		carCreatedProducer.sendMessage(carCreatedEvent);

		CreateCarResponse response = modelMapperService.forResponse().map(car, CreateCarResponse.class);
		return new SuccessDataResult<CreateCarResponse>(response, Messages.CarAdded);
	}

	@Override
	@PreAuthorize("hasRole('admin') or hasRole('developer') or hasRole('user')")
	public DataResult<UpdateCarResponse> update(UpdateCarRequest updateCarRequest) {
		checkIfCarPlateExits(updateCarRequest.getPlate());
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		carRespository.save(car);

		GetAllCarsResponse result = getById(car.getId());
		CarUpdatedEvent carUpdatedEvent = modelMapperService.forResponse().map(result, CarUpdatedEvent.class);
		carUpdatedEvent.setMessage(Messages.CarUpdated);
		carUpdatedProducer.sendMessage(carUpdatedEvent);

		UpdateCarResponse response = modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return new SuccessDataResult<UpdateCarResponse>(response, Messages.CarUpdated);
	}

	@Override
	@PreAuthorize("hasRole('admin') or hasRole('developer') or hasRole('user')")
	public GetAllCarsResponse getById(String carId) {
		Car car = carRespository.findById(carId).get();
		GetAllCarsResponse response = modelMapperService.forResponse().map(car, GetAllCarsResponse.class);
		return response;
	}

	@Override
	@PreAuthorize("hasRole('admin') or hasRole('developer') or hasRole('user')")
	public Result delete(String id) {
		carRespository.deleteById(id);
		CarDeletedEvent deletedEvent = new CarDeletedEvent();
		deletedEvent.setCarId(id);
		carDeletedProducer.sendMessage(deletedEvent);
		return new SuccessResult(Messages.CarDeleted);

	}

	@Override
	public void updateCarState(String carId, int state) {
		Car car = carRespository.findById(carId).get();
		car.setState(state);
		carRespository.save(car);
		GetAllCarsResponse result = getById(car.getId());
		CarUpdatedEvent carUpdatedEvent = modelMapperService.forResponse().map(result, CarUpdatedEvent.class);
		carUpdatedEvent.setMessage(Messages.CarUpdated);
		carUpdatedProducer.sendMessage(carUpdatedEvent);

	}

	private void checkIfCarPlateExits(String plate) {
		Car carPlate = carRespository.findByPlate(plate);
		if (carPlate != null) {
			throw new BusinessException("this plate has already been created");
		}
	}

}

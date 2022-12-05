package com.kodlamaio.invertoryService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.cars.CarCreatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.common.utilities.results.SuccessResult;
import com.kodlamaio.invertoryService.business.abstracts.CarService;
import com.kodlamaio.invertoryService.business.constans.Messages;
import com.kodlamaio.invertoryService.business.requests.create.CreateCarRequest;
import com.kodlamaio.invertoryService.business.requests.update.UpdateCarRequest;
import com.kodlamaio.invertoryService.business.responses.create.CreateCarResponse;
import com.kodlamaio.invertoryService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.invertoryService.business.responses.update.UpdateCarResponse;
import com.kodlamaio.invertoryService.dataAccess.CarRespository;
import com.kodlamaio.invertoryService.entities.Car;
import com.kodlamaio.invertoryService.kafka.producers.CarCreatedProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

	private CarRespository carRespository;
	private ModelMapperService modelMapperService;
	private CarCreatedProducer carCreatedProducer;

	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {

		List<Car> cars = carRespository.findAll();
		List<GetAllCarsResponse> responses = cars.stream()
				.map(car->modelMapperService.forResponse().map(car,GetAllCarsResponse.class)).toList();
		return new SuccessDataResult<List<GetAllCarsResponse>>(responses,Messages.CarListed);
	}

	@Override
	public DataResult<CreateCarResponse> add(CreateCarRequest createCarRequest) {
		
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());
		carRespository.save(car);

		GetAllCarsResponse result = getById(car.getId()).getData();
		CarCreatedEvent carCreatedEvent = modelMapperService.forResponse().map(result,CarCreatedEvent.class);
		carCreatedEvent.setMessage(Messages.CarAdded);
		carCreatedProducer.sendMessage(carCreatedEvent);
		
		CreateCarResponse response = modelMapperService.forResponse().map(car, CreateCarResponse.class);
		return new SuccessDataResult<CreateCarResponse>(response,Messages.CarAdded) ;
	}

	@Override
	public DataResult<UpdateCarResponse> update(UpdateCarRequest updateCarRequest) {
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		carRespository.save(car);
		
		UpdateCarResponse response = modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return new SuccessDataResult<UpdateCarResponse>(response,Messages.CarUpdated) ;
	}
	
	@Override
	public DataResult<GetAllCarsResponse> getById(String carId) {
		Car car = carRespository.findById(carId).get();
		GetAllCarsResponse response = modelMapperService.forResponse().map(car, GetAllCarsResponse.class);
		return new SuccessDataResult<GetAllCarsResponse>(response,Messages.CarListed) ;
	}


	@Override
	public Result delete(String id) {
		carRespository.deleteById(id);
		return new SuccessResult(Messages.CarDeleted);

	}

	@Override
	public void updateCarState(String carId,int state) {
		Car car = carRespository.findById(carId).get();
		car.setState(state);
		carRespository.save(car);
		
	}

	
	
	
	

}

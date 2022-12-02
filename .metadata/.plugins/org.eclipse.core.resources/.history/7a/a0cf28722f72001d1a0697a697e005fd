package com.kodlamaio.invertoryService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invertoryService.business.abstracts.CarService;
import com.kodlamaio.invertoryService.business.requests.create.CreateCarRequest;
import com.kodlamaio.invertoryService.business.requests.delete.DeleteCarRequest;
import com.kodlamaio.invertoryService.business.requests.update.UpdateCarRequest;
import com.kodlamaio.invertoryService.business.responses.create.CreateCarResponse;
import com.kodlamaio.invertoryService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.invertoryService.business.responses.update.UpdateCarResponse;
import com.kodlamaio.invertoryService.dataAccess.CarRespository;
import com.kodlamaio.invertoryService.entities.Car;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

	private CarRespository carRespository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllCarsResponse> getAll() {

		List<Car> cars = carRespository.findAll();
		List<GetAllCarsResponse> responses = cars.stream()
				.map(car->modelMapperService.forResponse().map(car,GetAllCarsResponse.class)).toList();
		return responses;
	}

	@Override
	public CreateCarResponse add(CreateCarRequest createCarRequest) {
		
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());
		carRespository.save(car);
		
		CreateCarResponse response = modelMapperService.forResponse().map(car, CreateCarResponse.class);
		return response;
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest updateCarRequest) {
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		carRespository.save(car);
		
		UpdateCarResponse response = modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return response;
	}

	@Override
	public void delete(DeleteCarRequest deleteCarRequest) {
		Car car = modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		carRespository.delete(car);

	}

	@Override
	public void updateCarState(String carId) {
		Car car = carRespository.findById(carId).get();
		car.setState(2);
		carRespository.save(car);
		
	}

	
	
	

}

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
import com.kodlamaio.invertoryService.dataAccess.CarFilterRepository;
import com.kodlamaio.invertoryService.dataAccess.CarRespository;
import com.kodlamaio.invertoryService.entities.Car;
import com.kodlamaio.invertoryService.entities.filter.CarFilter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

	private CarRespository carRespository;
	private ModelMapperService modelMapperService;
	private CarFilterRepository carFilterRepository;

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

		GetAllCarsResponse result = getById(car.getId());
		
		CarFilter carFilter = new CarFilter();
		carFilter.setCarId(result.getId());
		carFilter.setCarDailyPrice(result.getDailyPrice());
		carFilter.setCarModelYear(result.getModelYear());
		carFilter.setCarPlate(result.getPlate());
		carFilter.setCarModelId(result.getModelId());
		carFilter.setCarModelName(result.getModelName());
		carFilter.setCarModelBrandId(result.getModelBrandId());
		carFilter.setCarModelBrandName(result.getModelBrandName());
		carFilterRepository.insert(carFilter);
		
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
	public void updateCarState(String carId,int state) {
		Car car = carRespository.findById(carId).get();
		car.setState(state);
		carRespository.save(car);
		
	}

	@Override
	public GetAllCarsResponse getById(String carId) {
		Car car = carRespository.findById(carId).get();
		GetAllCarsResponse response = modelMapperService.forResponse().map(car, GetAllCarsResponse.class);
		return response;
	}

	
	
	

}

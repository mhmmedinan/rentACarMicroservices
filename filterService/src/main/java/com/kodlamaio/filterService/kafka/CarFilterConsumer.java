package com.kodlamaio.filterService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.cars.CarCreatedEvent;
import com.kodlamaio.common.events.cars.CarDeletedEvent;
import com.kodlamaio.common.events.cars.CarUpdatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.filterService.business.abstracts.CarFilterService;
import com.kodlamaio.filterService.entities.CarFilter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@EnableKafka
public class CarFilterConsumer {

	private CarFilterService carFilterService;
	private ModelMapperService modelMapperService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarFilterConsumer.class);
	
	@KafkaListener(topics = "create_car", groupId = "created_carFilter")
	public void consume(CarCreatedEvent carCreatedEvent) {
		CarFilter carFilter = modelMapperService.forRequest().map(carCreatedEvent,CarFilter.class);
		carFilterService.add(carFilter);
		LOGGER.info(String.format("Car Created Event Consume => %s", carCreatedEvent.toString()));
	}
	
	@KafkaListener(topics = "update_car", groupId = "updated_carFilter")
	public void consume(CarUpdatedEvent carUpdatedEvent) {
		CarFilter carFilter = modelMapperService.forRequest().map(carUpdatedEvent,CarFilter.class);
		carFilterService.update(carFilter);
		LOGGER.info(String.format("Car Updated Event Consume => %s", carUpdatedEvent.toString()));
	}
	
	@KafkaListener(topics = "delete_car", groupId = "deleted_carFilter")
	public void consume(CarDeletedEvent carDeletedEvent) {
		carFilterService.deleteCar(carDeletedEvent.getCarId());
		LOGGER.info(String.format("Car Deleted Event Consume => %s", carDeletedEvent.toString()));
	}
}

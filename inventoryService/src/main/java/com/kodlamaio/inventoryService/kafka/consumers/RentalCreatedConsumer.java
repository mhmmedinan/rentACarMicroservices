package com.kodlamaio.inventoryService.kafka.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rentals.RentalCreatedEvent;
import com.kodlamaio.inventoryService.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalCreatedConsumer {

	private CarService carService;


	private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreatedConsumer.class);

	@KafkaListener(topics = "create_rental", groupId = "created_rental")

	public void consume(RentalCreatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		carService.updateCarState(event.getCarId(),2);
	}
}
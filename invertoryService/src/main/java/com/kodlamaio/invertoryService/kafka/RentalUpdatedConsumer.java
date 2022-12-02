package com.kodlamaio.invertoryService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalUpdatedEvent;
import com.kodlamaio.invertoryService.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalUpdatedConsumer {

	private CarService carService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalUpdatedConsumer.class);

    @KafkaListener
    (
            topics = "${spring.kafka.topic.name}"
            ,groupId = "updated_rental"
    )
    
    public void consume(RentalUpdatedEvent event) {
    	LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
    	carService.updateCarState(event.getNewCarId());
    	
    }
    
   
}

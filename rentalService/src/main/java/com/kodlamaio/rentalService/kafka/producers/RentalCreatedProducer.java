package com.kodlamaio.rentalService.kafka.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rentals.RentalCreatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalCreatedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreatedProducer.class);

	private KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate;
	
	public void sendMessage(RentalCreatedEvent rentalCreatedEvent) {
		LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString()));
		
		Message<RentalCreatedEvent> message = MessageBuilder
				.withPayload(rentalCreatedEvent)
				.setHeader(KafkaHeaders.TOPIC, "create_rental").build();
		
		kafkaTemplate.send(message);
	}
}

package com.kodlamaiorentalService.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalUpdatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalUpdatedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentalUpdatedProducer.class);

	private NewTopic topic;
	
	private KafkaTemplate<String, RentalUpdatedEvent> kafkaTemplate;


	public void sendMessage(RentalUpdatedEvent rentalUpdatedEvent) {
		LOGGER.info(String.format("Rental updated event => %s", rentalUpdatedEvent.toString()));
		
		Message<RentalUpdatedEvent> message = MessageBuilder
				.withPayload(rentalUpdatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaTemplate.send(message);
	}
}

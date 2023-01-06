package com.kodlamaio.inventoryService.kafka.producers;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.cars.CarCreatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarCreatedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarCreatedProducer.class);

	private KafkaTemplate<String, CarCreatedEvent> kafkaTemplate;
	
	private final NewTopic topic;
	public void sendMessage(CarCreatedEvent carCreatedEvent) {
		LOGGER.info(String.format("Car created event => %s", carCreatedEvent.toString()));
		
		Message<CarCreatedEvent> message = MessageBuilder
				.withPayload(carCreatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(message);
	}

}

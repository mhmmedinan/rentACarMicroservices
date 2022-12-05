package com.kodlamaio.invertoryService.kafka.producers;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.cars.CarDeletedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarDeletedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarDeletedProducer.class);

	private NewTopic topic;

	private KafkaTemplate<String, CarDeletedEvent> kafkaTemplate;
	
	public void sendMessage(CarDeletedEvent carDeletedEvent) {
		LOGGER.info(String.format("Car deleted event => %s", carDeletedEvent.toString()));
		
		Message<CarDeletedEvent> message = MessageBuilder
				.withPayload(carDeletedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(message);
	}
}

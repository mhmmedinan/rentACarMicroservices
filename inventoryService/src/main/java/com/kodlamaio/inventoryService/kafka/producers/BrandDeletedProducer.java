package com.kodlamaio.inventoryService.kafka.producers;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.brands.BrandDeleteEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandDeletedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(BrandDeletedProducer.class);

	private final NewTopic topic;
	
	private KafkaTemplate<String, BrandDeleteEvent> kafkaTemplate;

	public void sendMessage(BrandDeleteEvent brandDeleteEvent) {
		LOGGER.info(String.format("Brand deleted event => %s", brandDeleteEvent.toString()));

		Message<BrandDeleteEvent> message = MessageBuilder.withPayload(brandDeleteEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(message);
	}
}

package com.kodlamaio.inventoryService.kafka.producers;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.models.ModelDeletedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelDeletedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelDeletedProducer.class);

	private KafkaTemplate<String, ModelDeletedEvent> kafkaTemplate;

	private final NewTopic topic;
	public void sendMessage(ModelDeletedEvent modelDeletedEvent) {
		LOGGER.info(String.format("Model deleted event => %s", modelDeletedEvent.toString()));

		Message<ModelDeletedEvent> message = MessageBuilder.withPayload(modelDeletedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(message);
	}
}

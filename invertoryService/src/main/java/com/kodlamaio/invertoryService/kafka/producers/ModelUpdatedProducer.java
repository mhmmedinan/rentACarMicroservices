package com.kodlamaio.invertoryService.kafka.producers;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.models.ModelUpdatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelUpdatedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelUpdatedProducer.class);

	private KafkaTemplate<String, ModelUpdatedEvent> kafkaTemplate;

	private final NewTopic topic;
	public void sendMessage(ModelUpdatedEvent modelUpdatedEvent) {
		LOGGER.info(String.format("Model updated event => %s", modelUpdatedEvent.toString()));

		Message<ModelUpdatedEvent> message = MessageBuilder.withPayload(modelUpdatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(message);
	}
}

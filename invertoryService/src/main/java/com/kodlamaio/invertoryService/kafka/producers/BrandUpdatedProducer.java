package com.kodlamaio.invertoryService.kafka.producers;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.brands.BrandUpdateEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandUpdatedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(BrandUpdatedProducer.class);

	private NewTopic topic;

	private KafkaTemplate<String, BrandUpdateEvent> kafkaTemplate;

	public void sendMessage(BrandUpdateEvent brandUpdateEvent) {
		LOGGER.info(String.format("Brand updated event => %s", brandUpdateEvent.toString()));

		Message<BrandUpdateEvent> message = MessageBuilder.withPayload(brandUpdateEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(message);
	}
}

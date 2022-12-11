package com.kodlamaiorentalService.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rentals.PaymentReceivedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentReceivedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentReceivedProducer.class);
	
	private final NewTopic topic;
	
	private KafkaTemplate<String, PaymentReceivedEvent> kafkaTemplate;
	
	public void sendMessage(PaymentReceivedEvent paymentReceivedEvent) {
		LOGGER.info(String.format("Payment received event => %s", paymentReceivedEvent.toString()));
		
		Message<PaymentReceivedEvent> message = MessageBuilder
				.withPayload(paymentReceivedEvent)
				.setHeader(KafkaHeaders.TOPIC, "received_payment").build();
		
		kafkaTemplate.send(message);
	}
}

package com.kodlamaio.invoiceService.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rentals.PaymentReceivedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.invoiceService.clients.CarServiceClient;
import com.kodlamaio.invoiceService.entities.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentReceivedConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentReceivedConsumer.class);
	private ModelMapperService modelMapperService;
	private CarServiceClient carServiceClient;
	private InvoiceService invoiceService;

	@KafkaListener(topics = "receive_payment", groupId = "payment_receive")
	public void consume(PaymentReceivedEvent event) {
		Invoice invoice = modelMapperService.forRequest().map(event, Invoice.class);
		GetAllCarsResponse response = carServiceClient.getByCarId(event.getCarId());
		invoice.setDailyPrice(event.getDailyPrice());
		invoice.setTotalPrice(event.getTotalPrice());
		invoice.setBrandName(response.getModelBrandName());
		invoice.setModelName(response.getModelName());
		invoice.setModelYear(response.getModelYear());
		invoiceService.createInvoice(invoice);
		LOGGER.info("Invoice created consume : {}", event.toString());
	}

}

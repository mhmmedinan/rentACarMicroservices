package com.kodlamaio.filterService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.models.ModelDeletedEvent;
import com.kodlamaio.common.events.models.ModelUpdatedEvent;
import com.kodlamaio.filterService.business.abstracts.CarFilterService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarFilterModelConsumer {

	private CarFilterService carFilterService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CarFilterModelConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "updated_model")
	public void consume(ModelUpdatedEvent modelUpdatedEvent) {
		carFilterService.getByModelId(modelUpdatedEvent.getModelId()).getData().forEach(filter -> {
			filter.setCarModelName(modelUpdatedEvent.getModelName());
			filter.setCarBrandId(modelUpdatedEvent.getBrandId());
			filter.setCarBrandName(
					carFilterService.getByBrandId(modelUpdatedEvent.getBrandId()).getData().get(0).getCarBrandName());
			carFilterService.add(filter);
		});
		LOGGER.info(String.format("Model Updated Event Consume => %s", modelUpdatedEvent.toString()));
	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "deleted_model")
	public void consume(ModelDeletedEvent modelDeletedEvent) {
		carFilterService.deleteModel(modelDeletedEvent.getModelId());
		LOGGER.info(String.format("Model Deleted Event Consume => %s", modelDeletedEvent.toString()));
	}
}

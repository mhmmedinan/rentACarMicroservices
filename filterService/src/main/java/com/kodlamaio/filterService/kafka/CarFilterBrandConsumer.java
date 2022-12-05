package com.kodlamaio.filterService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.brands.BrandDeleteEvent;
import com.kodlamaio.common.events.brands.BrandUpdateEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.filterService.business.abstracts.CarFilterService;
import com.kodlamaio.filterService.entities.CarFilter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarFilterBrandConsumer {

	private CarFilterService carFilterService;
	private ModelMapperService modelMapperService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarFilterBrandConsumer.class);

	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "updated_brand")
	public void consume(BrandUpdateEvent brandUpdateEvent) {
		CarFilter carFilter = modelMapperService.forRequest().map(brandUpdateEvent,CarFilter.class);
		carFilterService.updateBrand(carFilter);
		LOGGER.info(String.format("Brand Updated Event Consume => %s", brandUpdateEvent.toString()));
	}
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "deleted_brand")
	public void consume(BrandDeleteEvent brandDeleteEvent) {
		carFilterService.deleteBrand(brandDeleteEvent.getBrandId());
		LOGGER.info(String.format("Brand Deleted Event Consume => %s", brandDeleteEvent.toString()));
	}

}

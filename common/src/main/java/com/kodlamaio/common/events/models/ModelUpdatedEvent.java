package com.kodlamaio.common.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelUpdatedEvent {

	private String modelId;
	private String modelName;
	private String brandId;
	private String message;
}

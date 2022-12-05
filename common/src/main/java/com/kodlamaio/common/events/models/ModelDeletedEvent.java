package com.kodlamaio.common.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelDeletedEvent {

	private String modelId;
	
	private String message;
	
}

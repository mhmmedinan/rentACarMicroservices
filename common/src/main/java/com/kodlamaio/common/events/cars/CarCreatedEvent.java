package com.kodlamaio.common.events.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCreatedEvent {

	private String id;
	private double dailyPrice;
	private int modelYear;
	private String plate;
	private String modelId;
	private String modelBrandId;
	private String modelBrandName;
	private String modelName;
	private int state;

	private String message;
}

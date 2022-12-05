package com.kodlamaio.common.events.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandUpdateEvent {

	private String carBrandId;
	private String carBrandName;
	
	private String message;
}

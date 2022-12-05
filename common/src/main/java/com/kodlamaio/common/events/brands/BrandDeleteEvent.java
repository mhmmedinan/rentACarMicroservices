package com.kodlamaio.common.events.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDeleteEvent {

	private String brandId;
	private String message;
}

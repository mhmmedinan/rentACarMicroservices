package com.kodlamaio.invoiceService.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="invoices")
public class Invoice {

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="carId")
	private String carId;
	
	@Column(name="fullName")
	private String fullName;
	
	@Column(name="modelName")
	private String modelName;
	
	@Column(name="brandName")
	private String brandName;
	
	@Column(name="modelYear")
	private int modelYear;
	
	@Column(name="dailyPrice")
	private double dailyPrice;
	
	@Column(name="totalPrice")
	private double totalPrice;
	
	@Column(name="rentedForDays")
	private int rentedForDays;
	
	@Column(name="rentedDate")
	private LocalDate rentedDate;
}
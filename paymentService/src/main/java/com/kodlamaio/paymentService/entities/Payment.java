package com.kodlamaio.paymentService.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payments")
public class Payment {
	
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "cardName")
	private String cardName;

	@Column(name = "cardNumber")
	private String cardNumber;

	@Column(name = "expirationDate")
	private String expirationDate;

	@Column(name = "cvv")
	private String cvv;

	@Column(name = "balance")
	private double balance;
}

package com.kodlamaio.rentalService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentalService.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental,String>{

	
}


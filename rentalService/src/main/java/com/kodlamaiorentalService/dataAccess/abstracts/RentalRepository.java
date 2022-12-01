package com.kodlamaiorentalService.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaiorentalService.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental,String>{

}

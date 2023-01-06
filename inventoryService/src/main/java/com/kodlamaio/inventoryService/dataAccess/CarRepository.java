package com.kodlamaio.inventoryService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryService.entities.Car;

public interface CarRepository extends JpaRepository<Car,String>{

	Car findByPlate(String plate);

}

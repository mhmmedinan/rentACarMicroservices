package com.kodlamaio.filterService.dataAccess.abstracts;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.filterService.entities.CarFilter;

public interface CarFilterRepository extends MongoRepository<CarFilter,String> {

}

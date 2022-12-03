package com.kodlamaio.invertoryService.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.invertoryService.entities.filter.CarFilter;

public interface CarFilterRepository extends MongoRepository<CarFilter,String> {

}

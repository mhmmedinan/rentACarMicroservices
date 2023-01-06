package com.kodlamaio.inventoryService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryService.entities.Model;

public interface ModelRepository extends JpaRepository<Model,String> {

	Model findByName(String name);
}

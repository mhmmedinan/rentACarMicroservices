package com.kodlamaio.inventoryService.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryService.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {

	List<Brand> getByName(String name);
	Brand findByName(String name);
}


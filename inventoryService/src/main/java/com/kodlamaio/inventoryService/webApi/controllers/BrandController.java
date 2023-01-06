package com.kodlamaio.inventoryService.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.inventoryService.business.abstracts.BrandService;
import com.kodlamaio.inventoryService.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryService.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryService.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryService.business.responses.update.UpdateBrandResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandController {

	private BrandService brandService;

	@GetMapping()
	public ResponseEntity<?> getAll() {
		DataResult<List<GetAllBrandsResponse>> result = brandService.getAll();
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
		DataResult<CreateBrandResponse> result = brandService.add(createBrandRequest);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		DataResult<UpdateBrandResponse> result = brandService.update(updateBrandRequest);

		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		Result result = brandService.delete(id);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

}

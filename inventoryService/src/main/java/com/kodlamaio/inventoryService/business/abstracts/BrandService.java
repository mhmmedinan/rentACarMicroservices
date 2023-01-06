package com.kodlamaio.inventoryService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.inventoryService.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryService.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryService.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryService.business.responses.update.UpdateBrandResponse;

public interface BrandService {

	DataResult<List<GetAllBrandsResponse>> getAll();
	DataResult<GetAllBrandsResponse> getById(String id);
	DataResult<List<GetAllBrandsResponse>> getByName(String name);
	DataResult<CreateBrandResponse> add(CreateBrandRequest createBrandRequest);
	DataResult<UpdateBrandResponse> update(UpdateBrandRequest updateBrandRequest);
	Result delete(String id);
}

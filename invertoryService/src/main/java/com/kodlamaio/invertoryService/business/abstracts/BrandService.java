package com.kodlamaio.invertoryService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.invertoryService.business.requests.create.CreateBrandRequest;
import com.kodlamaio.invertoryService.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.invertoryService.business.responses.create.CreateBrandResponse;
import com.kodlamaio.invertoryService.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.invertoryService.business.responses.update.UpdateBrandResponse;

public interface BrandService {

	DataResult<List<GetAllBrandsResponse>> getAll();
	DataResult<List<GetAllBrandsResponse>> getByName(String name);
	DataResult<CreateBrandResponse> add(CreateBrandRequest createBrandRequest);
	DataResult<UpdateBrandResponse> update(UpdateBrandRequest updateBrandRequest);
	Result delete(String id);
}

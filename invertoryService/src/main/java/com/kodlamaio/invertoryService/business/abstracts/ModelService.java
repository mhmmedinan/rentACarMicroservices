package com.kodlamaio.invertoryService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.invertoryService.business.requests.create.CreateModelRequest;
import com.kodlamaio.invertoryService.business.requests.update.UpdateModelRequest;
import com.kodlamaio.invertoryService.business.responses.create.CreateModelResponse;
import com.kodlamaio.invertoryService.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.invertoryService.business.responses.update.UpdateModelResponse;

public interface ModelService {

	DataResult<List<GetAllModelsResponse>> getAll();
	DataResult<GetAllModelsResponse> getById(String id);
	DataResult<CreateModelResponse> add(CreateModelRequest createModelRequest);
	DataResult<UpdateModelResponse> update(UpdateModelRequest updateModelRequest);
	Result delete(String id);
}

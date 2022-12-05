package com.kodlamaio.invertoryService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.common.utilities.results.SuccessResult;
import com.kodlamaio.invertoryService.business.abstracts.ModelService;
import com.kodlamaio.invertoryService.business.constans.Messages;
import com.kodlamaio.invertoryService.business.requests.create.CreateModelRequest;
import com.kodlamaio.invertoryService.business.requests.update.UpdateModelRequest;
import com.kodlamaio.invertoryService.business.responses.create.CreateModelResponse;
import com.kodlamaio.invertoryService.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.invertoryService.business.responses.update.UpdateModelResponse;
import com.kodlamaio.invertoryService.dataAccess.ModelRepository;
import com.kodlamaio.invertoryService.entities.Model;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllModelsResponse>> getAll() {
		List<Model> models = modelRepository.findAll();
		List<GetAllModelsResponse> responses = models.stream()
				.map(model -> modelMapperService.forResponse().map(model, GetAllModelsResponse.class)).toList();
		return new SuccessDataResult<List<GetAllModelsResponse>>(responses, Messages.ModelListed);
	}

	@Override
	public DataResult<CreateModelResponse> add(CreateModelRequest createModelRequest) {
		Model model = modelMapperService.forRequest().map(createModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());
		modelRepository.save(model);

		CreateModelResponse response = modelMapperService.forResponse().map(model, CreateModelResponse.class);
		return new SuccessDataResult<CreateModelResponse>(response,Messages.ModelAdded);
	}

	@Override
	public DataResult<UpdateModelResponse> update(UpdateModelRequest updateModelRequest) {
		Model model = modelMapperService.forRequest().map(updateModelRequest, Model.class);
		modelRepository.save(model);
		
		UpdateModelResponse response = modelMapperService.forResponse().map(model, UpdateModelResponse.class);
		return new SuccessDataResult<UpdateModelResponse>(response,Messages.ModelUpdated);
	}

	@Override
	public Result delete(String id) {
		modelRepository.deleteById(id);
		return new SuccessResult(Messages.ModelDeleted);

	}

}

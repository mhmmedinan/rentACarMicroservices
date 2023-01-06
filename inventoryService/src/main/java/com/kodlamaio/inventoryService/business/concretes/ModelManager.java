package com.kodlamaio.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.models.ModelDeletedEvent;
import com.kodlamaio.common.events.models.ModelUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.common.utilities.results.SuccessResult;
import com.kodlamaio.inventoryService.business.abstracts.ModelService;
import com.kodlamaio.inventoryService.business.constans.Messages;
import com.kodlamaio.inventoryService.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryService.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryService.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryService.business.responses.update.UpdateModelResponse;
import com.kodlamaio.inventoryService.dataAccess.ModelRepository;
import com.kodlamaio.inventoryService.entities.Model;
import com.kodlamaio.inventoryService.kafka.producers.ModelDeletedProducer;
import com.kodlamaio.inventoryService.kafka.producers.ModelUpdatedProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	private ModelDeletedProducer modelDeletedProducer;
	private ModelUpdatedProducer modelUpdatedProducer;

	@Override
	public DataResult<List<GetAllModelsResponse>> getAll() {
		List<Model> models = modelRepository.findAll();
		List<GetAllModelsResponse> responses = models.stream()
				.map(model -> modelMapperService.forResponse().map(model, GetAllModelsResponse.class)).toList();
		return new SuccessDataResult<List<GetAllModelsResponse>>(responses, Messages.ModelListed);
	}

	@Override
	public DataResult<CreateModelResponse> add(CreateModelRequest createModelRequest) {
		checkIfByModelNameExists(createModelRequest.getName());
		Model model = modelMapperService.forRequest().map(createModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());
		modelRepository.save(model);

		CreateModelResponse response = modelMapperService.forResponse().map(model, CreateModelResponse.class);
		return new SuccessDataResult<CreateModelResponse>(response,Messages.ModelAdded);
	}

	@Override
	public DataResult<UpdateModelResponse> update(UpdateModelRequest updateModelRequest) {
		checkIfByModelNameExists(updateModelRequest.getName());
		Model model = modelMapperService.forRequest().map(updateModelRequest, Model.class);
		modelRepository.save(model);
		
		GetAllModelsResponse result = getById(model.getId()).getData();
		ModelUpdatedEvent modelUpdatedEvent = new ModelUpdatedEvent();
		modelUpdatedEvent.setModelId(result.getId());
		modelUpdatedEvent.setModelName(result.getName());
		modelUpdatedEvent.setBrandId(result.getBrandId());
		modelUpdatedEvent.setMessage(Messages.ModelUpdated);
		modelUpdatedProducer.sendMessage(modelUpdatedEvent);
		
		UpdateModelResponse response = modelMapperService.forResponse().map(model, UpdateModelResponse.class);
		return new SuccessDataResult<UpdateModelResponse>(response,Messages.ModelUpdated);
	}

	@Override
	public Result delete(String id) {
		modelRepository.deleteById(id);
		ModelDeletedEvent deletedEvent = new ModelDeletedEvent();
		deletedEvent.setModelId(id);
		deletedEvent.setMessage(Messages.ModelDeleted);
		modelDeletedProducer.sendMessage(deletedEvent);
		return new SuccessResult(Messages.ModelDeleted);

	}
	
	private void checkIfByModelNameExists(String name) {
		Model model = modelRepository.findByName(name);
		if (model!=null) {
			throw new BusinessException("this model name has already been created");
		}
	}

	@Override
	public DataResult<GetAllModelsResponse> getById(String id) {
		Model model = modelRepository.findById(id).get();
		GetAllModelsResponse response = modelMapperService.forResponse().map(model, GetAllModelsResponse.class);
		return new SuccessDataResult<GetAllModelsResponse>(response);
	}

}

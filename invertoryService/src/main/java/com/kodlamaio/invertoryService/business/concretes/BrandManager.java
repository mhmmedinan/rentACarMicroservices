package com.kodlamaio.invertoryService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.brands.BrandDeleteEvent;
import com.kodlamaio.common.events.brands.BrandUpdateEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.Result;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.common.utilities.results.SuccessResult;
import com.kodlamaio.invertoryService.business.abstracts.BrandService;
import com.kodlamaio.invertoryService.business.constans.Messages;
import com.kodlamaio.invertoryService.business.requests.create.CreateBrandRequest;
import com.kodlamaio.invertoryService.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.invertoryService.business.responses.create.CreateBrandResponse;
import com.kodlamaio.invertoryService.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.invertoryService.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.invertoryService.dataAccess.BrandRepository;
import com.kodlamaio.invertoryService.entities.Brand;
import com.kodlamaio.invertoryService.kafka.producers.BrandDeletedProducer;
import com.kodlamaio.invertoryService.kafka.producers.BrandUpdatedProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	private BrandDeletedProducer brandDeletedProducer;
	private BrandUpdatedProducer brandUpdatedProducer;

	@Override
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		List<Brand> brands = brandRepository.findAll();
		List<GetAllBrandsResponse> responses = brands.stream()
				.map(brand -> modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)).toList();
		return new SuccessDataResult<List<GetAllBrandsResponse>>(responses, Messages.BrandListed);
	}

	@Override
	public DataResult<CreateBrandResponse> add(CreateBrandRequest createBrandRequest) {
		checkIfByBrandNameExists(createBrandRequest.getName());
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brand.setId(UUID.randomUUID().toString());
		brandRepository.save(brand);

		CreateBrandResponse createBrandResponse = modelMapperService.forResponse().map(brand,
				CreateBrandResponse.class);
		return new SuccessDataResult<CreateBrandResponse>(createBrandResponse, Messages.BrandAdded);
	}

	@Override
	public DataResult<List<GetAllBrandsResponse>> getByName(String name) {
		List<Brand> brands = brandRepository.getByName(name);
		List<GetAllBrandsResponse> responses = brands.stream()
				.map(brand -> modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)).toList();
		return new SuccessDataResult<List<GetAllBrandsResponse>>(responses, Messages.BrandListed);
	}

	@Override
	public DataResult<UpdateBrandResponse> update(UpdateBrandRequest updateBrandRequest) {
		checkIfByBrandNameExists(updateBrandRequest.getName());
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandRepository.save(brand);
		
		GetAllBrandsResponse result = getById(brand.getId()).getData();
		BrandUpdateEvent brandUpdateEvent = new BrandUpdateEvent();
		brandUpdateEvent.setCarBrandId(result.getId());
		brandUpdateEvent.setCarBrandName(result.getName());
		brandUpdateEvent.setMessage(Messages.BrandUpdated);
		brandUpdatedProducer.sendMessage(brandUpdateEvent);

		UpdateBrandResponse response = modelMapperService.forResponse().map(brand, UpdateBrandResponse.class);
		return new SuccessDataResult<UpdateBrandResponse>(response, Messages.BrandUpdated);
	}

	@Override
	public DataResult<GetAllBrandsResponse> getById(String id) {
		Brand brand = brandRepository.findById(id).get();
		GetAllBrandsResponse response = modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class);
		return new SuccessDataResult<GetAllBrandsResponse>(response);
	}
	@Override
	public Result delete(String id) {
		brandRepository.deleteById(id);
		
		BrandDeleteEvent brandDeleteEvent = new BrandDeleteEvent();
		brandDeleteEvent.setBrandId(id);
		brandDeleteEvent.setMessage(Messages.BrandDeleted);
		brandDeletedProducer.sendMessage(brandDeleteEvent);
		
		return new SuccessResult(Messages.BrandDeleted);
	}
	
	
	private void checkIfByBrandNameExists(String name) {
		Brand brand = brandRepository.findByName(name);
		if (brand!=null) {
			throw new BusinessException("this brand name has already been created");
		}
	}

	

}

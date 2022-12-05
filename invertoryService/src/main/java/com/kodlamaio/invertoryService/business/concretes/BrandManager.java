package com.kodlamaio.invertoryService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		List<Brand> brands = brandRepository.findAll();
		List<GetAllBrandsResponse> responses = brands.stream()
				.map(brand -> modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)).toList();
		return new SuccessDataResult<List<GetAllBrandsResponse>>(responses, Messages.BrandListed);
	}

	@Override
	public DataResult<CreateBrandResponse> add(CreateBrandRequest createBrandRequest) {

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
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandRepository.save(brand);

		UpdateBrandResponse response = modelMapperService.forResponse().map(brand, UpdateBrandResponse.class);
		return new SuccessDataResult<UpdateBrandResponse>(response, Messages.BrandUpdated);
	}

	@Override
	public Result delete(String id) {
		brandRepository.deleteById(id);
		return new SuccessResult(Messages.BrandDeleted);
	}

}

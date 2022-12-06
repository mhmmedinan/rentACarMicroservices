package com.kodlamaio.invoiceService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.business.responses.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceService.entities.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
	
	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
	
	@Override
	public DataResult<CreateInvoiceResponse> add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setId(UUID.randomUUID().toString());
		invoiceRepository.save(invoice);
		
		CreateInvoiceResponse createInvoiceResponse = modelMapperService.forResponse().map(invoice, CreateInvoiceResponse.class);
		return new SuccessDataResult<CreateInvoiceResponse>(createInvoiceResponse);
	}

	@Override
	public void createInvoice(Invoice invoice) {
		invoice.setId(UUID.randomUUID().toString());
		invoiceRepository.save(invoice);
		
	}

	@Override
	public DataResult<List<GetAllInvoiceResponse>> getAll() {
		List<Invoice> invoices = invoiceRepository.findAll();
		List<GetAllInvoiceResponse> responses = invoices.stream().map(invoice->modelMapperService.forResponse().map(invoice, GetAllInvoiceResponse.class)).toList();
	    return new SuccessDataResult<List<GetAllInvoiceResponse>>(responses);
	}

}

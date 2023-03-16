package com.kodlamaio.invoiceService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.dto.CustomerRequest;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.requests.create.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.responses.create.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.business.responses.get.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceService.entities.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
	
	@Override
	@PreAuthorize("hasRole('admin') or hasRole('developer') or hasRole('user')")
	public DataResult<List<GetAllInvoiceResponse>> getAll() {
		List<Invoice> invoices = invoiceRepository.findAll();
		List<GetAllInvoiceResponse> responses = invoices.stream().
				map(invoice->modelMapperService.forResponse().
						map(invoice, GetAllInvoiceResponse.class)).toList();
	    return new SuccessDataResult<List<GetAllInvoiceResponse>>(responses);
	}

	@Override
	public DataResult<CreateInvoiceResponse> add(CreateInvoiceRequest createInvoiceRequest,CustomerRequest customerRequest) {
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setId(UUID.randomUUID().toString());
		setCustomer(customerRequest, invoice);
		invoiceRepository.save(invoice);
		
		CreateInvoiceResponse createInvoiceResponse = modelMapperService.forResponse().map(invoice, CreateInvoiceResponse.class);
		return new SuccessDataResult<CreateInvoiceResponse>(createInvoiceResponse);
	}

	@Override
	public void createInvoice(Invoice invoice) {
		invoice.setId(UUID.randomUUID().toString());
		invoiceRepository.save(invoice);
	}
	
	 private static void setCustomer(CustomerRequest customerRequest, Invoice invoice) {
	        invoice.setCustomerId(customerRequest.getCustomerId());
	        invoice.setCustomerUserName(customerRequest.getCustomerUserName());
	        invoice.setCustomerFirstName(customerRequest.getCustomerFirstName());
	        invoice.setCustomerLastName(customerRequest.getCustomerLastName());
	        invoice.setCustomerEmail(customerRequest.getCustomerEmail());
	    }

}

package com.kodlamaio.invoiceService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.dto.CustomerRequest;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.invoiceService.business.requests.create.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.responses.create.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.business.responses.get.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.entities.Invoice;

public interface InvoiceService {

	DataResult<List<GetAllInvoiceResponse>> getAll();
	DataResult<CreateInvoiceResponse> add(CreateInvoiceRequest createInvoiceRequest,CustomerRequest customerRequest);
	void createInvoice(Invoice invoice);
}

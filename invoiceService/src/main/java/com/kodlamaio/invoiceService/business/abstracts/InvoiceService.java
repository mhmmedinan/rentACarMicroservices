package com.kodlamaio.invoiceService.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.invoiceService.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.business.responses.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.entities.Invoice;

public interface InvoiceService {

	DataResult<List<GetAllInvoiceResponse>> getAll();
	DataResult<CreateInvoiceResponse> add(CreateInvoiceRequest createInvoiceRequest);
	void createInvoice(Invoice invoice);
}

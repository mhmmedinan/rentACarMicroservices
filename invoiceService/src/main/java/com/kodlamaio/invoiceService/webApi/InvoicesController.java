package com.kodlamaio.invoiceService.webApi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.responses.GetAllInvoiceResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
public class InvoicesController {

	private InvoiceService invoiceService;
	
	@GetMapping("getall")
	public ResponseEntity<?> getAll(){
		DataResult<List<GetAllInvoiceResponse>> result = invoiceService.getAll();
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		
		return ResponseEntity.badRequest().body(result);
	}
	
	
}

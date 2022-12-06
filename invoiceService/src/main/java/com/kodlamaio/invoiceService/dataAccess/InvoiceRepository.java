package com.kodlamaio.invoiceService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.invoiceService.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,String> {

}

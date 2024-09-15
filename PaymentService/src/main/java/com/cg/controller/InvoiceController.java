package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Invoice;
import com.cg.exception.InvoiceNotFoundException;
import com.cg.service.InvoiceServiceImpl;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceServiceImpl invoiceService;
	
	@PostMapping("/createinvoice/{paymentId}/{orderId}")
	@PreAuthorize("hasAnyAuthority('dealer')")
    public ResponseEntity<Invoice> createInvoice(@PathVariable int paymentId, @PathVariable int orderId) throws InvoiceNotFoundException {
        Invoice invoice = invoiceService.createInvoice(paymentId, orderId);
        return ResponseEntity.ok(invoice);
    }
	
	@GetMapping("/displayAll")
	@PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<List<Invoice>> displayAll() throws InvoiceNotFoundException {
        List<Invoice> displayAll = invoiceService.displayAll();
        return ResponseEntity.ok(displayAll);
    }
	
	@GetMapping("/displayByInvoiceId/{invoiceId}")
	@PreAuthorize("hasAnyAuthority('dealer')")
	public ResponseEntity<Invoice> displayById(@PathVariable int invoiceId) throws InvoiceNotFoundException {
        Invoice display = invoiceService.displayBYInvoiceId(invoiceId);
        return ResponseEntity.ok(display);
    }

}

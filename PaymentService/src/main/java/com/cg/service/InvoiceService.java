package com.cg.service;

import java.util.List;

import com.cg.entity.Invoice;
import com.cg.exception.InvoiceNotFoundException;

public interface InvoiceService {
	
	public Invoice createInvoice(long paymentId, int orderId) throws InvoiceNotFoundException;
	
	public List<Invoice> displayAll() throws InvoiceNotFoundException;
	
	public Invoice displayBYInvoiceId(int invoiceId) throws InvoiceNotFoundException;

}

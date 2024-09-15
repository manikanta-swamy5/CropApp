package com.cg.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Invoice;
import com.cg.entity.OrderDTO;
import com.cg.entity.Payment;
import com.cg.exception.InvoiceNotFoundException;
import com.cg.repository.InvoiceRepository;
import com.cg.repository.PaymentRepository;
import com.cg.secure.feign.OrderFeignClient;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderFeignClient orderFeignClient;

	public Invoice createInvoice(long paymentId, int orderId) throws InvoiceNotFoundException {
		
		Optional<Payment> byId = paymentRepository.findById(paymentId);
		Payment payment = byId.get();
		OrderDTO orderDto = orderFeignClient.getOrderDetails(orderId);
		Invoice invoice = new Invoice();
		invoice.setCropId(orderDto.getCropId());
		invoice.setCreatedDate(LocalDateTime.now());
		invoice.setPayment(payment);

		Invoice save = invoiceRepository.save(invoice);
		return save;
	}

	@Override
	public List<Invoice> displayAll() throws InvoiceNotFoundException {
		// TODO Auto-generated method stub
		List<Invoice> all = invoiceRepository.findAll();
		if(all.isEmpty()) {
			throw new InvoiceNotFoundException("Invoice Not Found");
		}else {
			return all;
		}
	}

	public Invoice displayBYInvoiceId(int invoiceId) throws InvoiceNotFoundException {
		// TODO Auto-generated method stub
		
		if(invoiceRepository.existsById(invoiceId)) {
			return invoiceRepository.findByInvoiceId(invoiceId);
			
		}else {
			throw new InvoiceNotFoundException("Invocie Not Found");
		}
	}
	
	

}

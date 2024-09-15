package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Payment;
import com.cg.exception.PaymentNotFoundException;

public interface PaymentService {
	
	public Payment createPaymentFromOrder(int orderId, long accNo) throws PaymentNotFoundException;
	
	public Payment findPaymentByDealerName(String dealerName) throws PaymentNotFoundException;
	
	public List<Payment> findByTotalPriceGreaterThan(double totalPrice) throws PaymentNotFoundException;
	
	public List<Payment> findByTotalPriceLessThan(double totalPrice) throws PaymentNotFoundException;
	
	public List<Payment> findByOrderDateAfter(LocalDate orderDate) throws PaymentNotFoundException;
	
	public List<Payment> findByOrderDateBefore(LocalDate orderDate) throws PaymentNotFoundException;
	
	public List<Payment> getLast2Transactions() throws PaymentNotFoundException;

}

package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.entity.OrderDTO;
import com.cg.entity.Payment;
import com.cg.exception.PaymentNotFoundException;
import com.cg.repository.PaymentRepository;
import com.cg.secure.feign.BankFeignClient;
import com.cg.secure.feign.OrderFeignClient;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderFeignClient orderFeignClient;
	
	@Autowired
	private BankFeignClient bankFeignClient;


	public Payment createPaymentFromOrder(int orderId, long accNo) throws PaymentNotFoundException {

		OrderDTO orderDto = orderFeignClient.getOrderDetails(orderId);
		Payment byOrderId = paymentRepository.findByOrderId(orderDto.getOrderId());

		if(byOrderId == null && "Accepted".equals(orderDto.getStatus())) {
			Payment payment = new Payment();
            payment.setOrderId(orderDto.getOrderId());
            payment.setTotalPrice(orderDto.getTotalPrice());
            payment.setStatus("Completed");
            payment.setDealerName(orderDto.getDealerName());
            payment.setOrderDate(orderDto.getDate());
            
            
//            ResponseEntity<OrderDTO> byDealer = orderFeignClient.updateStatusByDealer(orderId);
//            System.out.println(byDealer.getBody());
            ResponseEntity<String> amount = bankFeignClient.addAmount(accNo, orderDto.getTotalPrice());
            System.out.println(amount.getBody());
            
			return paymentRepository.save(payment);
		}
		else {
			throw new PaymentNotFoundException("Order Id already present or status is not Accepted");
		}

	}

	public Payment findPaymentByDealerName(String dealerName) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		if(paymentRepository.existsByDealerName(dealerName)) {
		return paymentRepository.findByDealerName(dealerName);
		}else {
			throw new PaymentNotFoundException("Payment Not Found");
		}
	}

	public List<Payment> findByTotalPriceGreaterThan(double totalPrice) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
			List<Payment> byTotalPriceGreaterThan = paymentRepository.findByTotalPriceGreaterThan(totalPrice);
			if(byTotalPriceGreaterThan.isEmpty()) {
				throw new PaymentNotFoundException("Payment Not Found");
			}else {
				return byTotalPriceGreaterThan;
			}
		
	}
	
	@Override
	public List<Payment> findByTotalPriceLessThan(double totalPrice) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		List<Payment> byTotalPriceLessThan = paymentRepository.findByTotalPriceLessThan(totalPrice);
		if(byTotalPriceLessThan.isEmpty()) {
			throw new PaymentNotFoundException("Payment Not Found");
		}else {
			return byTotalPriceLessThan;
		}
	}
	
	@Override
	public List<Payment> findByOrderDateBefore(LocalDate orderDate) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		List<Payment> byOrderDateBefore = paymentRepository.findByOrderDateBefore(orderDate);
		if(byOrderDateBefore.isEmpty()) {
			throw new PaymentNotFoundException("Payment Not Found");
		}else {
			return byOrderDateBefore;
		}
	}

	public List<Payment> findByOrderDateAfter(LocalDate orderDate) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		List<Payment> byOrderDateAfter = paymentRepository.findByOrderDateAfter(orderDate);
		if(byOrderDateAfter.isEmpty()) {
			throw new PaymentNotFoundException("Payment Not Found");
		}else {
			return byOrderDateAfter;
		}
	}

	public List<Payment> getLast2Transactions() throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		List<Payment> top2ByOrderByOrderDateDesc = paymentRepository.findTop2ByOrderByOrderDateDesc();
		if(top2ByOrderByOrderDateDesc.isEmpty()) {
			throw new PaymentNotFoundException("Payment Not found");
		}else {
			return top2ByOrderByOrderDateDesc;
		}
	}


}

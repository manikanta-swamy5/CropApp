package com.cg.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByDealerName(String dealerName);

	List<Payment> findByTotalPriceGreaterThan(double totalPrice);
	
	List<Payment> findByTotalPriceLessThan(double totalPrice);
	
	Payment findByOrderId(int orderId);
	
	List<Payment> findByOrderDateBefore(LocalDate orderDate);

	List<Payment> findByOrderDateAfter(LocalDate orderDate);
	
	List<Payment> findTop2ByOrderByOrderDateDesc();

	boolean existsByDealerName(String dealerName);	

}

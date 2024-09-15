package com.cg.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	public List<Orders> findByDate(LocalDate date);

	public List<Orders> findByDateBefore(LocalDate date);

	public List<Orders> findByDateBetween(LocalDate startDate, LocalDate endDate);

	public Optional<Orders> findByCropId(int cropId);
	
	public List<Orders> findByTotalPrice(double price);

	List<Orders> findByDealerName(String name);
}

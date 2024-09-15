package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Orders;
import com.cg.exception.OrderNotFoundException;

public interface OrderService {
	
	public Orders createOrder(int cropId, String name) throws OrderNotFoundException;
	
	public Orders getOrder(int orderId) throws OrderNotFoundException;
	
	public String deleteOrder(int orderId) throws OrderNotFoundException;

	public List<Orders> findByDate(LocalDate date)throws OrderNotFoundException;

	public List<Orders> findByDateBefore(LocalDate date)throws OrderNotFoundException;
	
	public List<Orders> findByDateBetween(LocalDate startDate, LocalDate endDate) throws OrderNotFoundException;

    public List<Orders> findByTotalPrice(double price) throws OrderNotFoundException;
    
    public Orders updateStatus(int orderId);
    
    public Orders updateStatusByDealer(int orderId);
    
    List<Orders> findByDealerName(String name);

}

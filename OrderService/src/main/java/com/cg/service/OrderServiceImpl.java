package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.CropDto;
import com.cg.entity.Orders;
import com.cg.exception.OrderNotFoundException;
import com.cg.feign.CropClient;
import com.cg.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepo;
	
	
	@Autowired
	CropClient client;

		
	public Orders createOrder(int cropId, String name) throws OrderNotFoundException{
		CropDto cropById = client.getCropById(cropId);
			
			Optional<Orders> byCropId = orderRepo.findByCropId(cropId);
			if(byCropId.isEmpty()) {
				Orders newOrders = new Orders();
				newOrders.setCropId(cropById.getCropId());
				newOrders.setDate(LocalDate.now());
				newOrders.setDealerName(name);
				newOrders.setQuantity(cropById.getQuantity());
				newOrders.setTotalPrice(cropById.getPrice());
				newOrders.setStatus("Ordered");
				
				Orders order = orderRepo.save(newOrders);
				log.info("Order created successfully");
				
				return order;
			}else {
				log.error("Order not found");
				throw new OrderNotFoundException("Crop not found to create order");
			}
			
			
		}	
	
	

	public Orders getOrder(int orderId) throws OrderNotFoundException{
		Optional<Orders> byId = orderRepo.findById(orderId);
		if(byId.isPresent()) {
		Orders orders = byId.get();
		log.info("Order created success");
		return orders; 
		}else {
			log.error("Order with id "+orderId+" not found");
			throw new OrderNotFoundException("Order with "+orderId+" not found");
		}
	}

	
	public String deleteOrder(int orderId) throws OrderNotFoundException {
		Optional<Orders> byId = orderRepo.findById(orderId);
		if(byId.isPresent()) {
			orderRepo.deleteById(orderId);
			log.info("Order with Id:"+orderId+" deleted successfully");
			return "Order with Id:"+orderId+" deleted successfully";
		}else {
			log.error("Order with OrderId:"+orderId+" not found");
			throw new OrderNotFoundException("Order with OrderId:"+orderId+" not found");
		}
		
	}


	public List<Orders> findByDate(LocalDate date) throws OrderNotFoundException { 
		 List<Orders> orders = orderRepo.findByDate(date); 
	        
	        if (orders.isEmpty()) {
	        	log.error("Order not found with "+date);
	            throw new OrderNotFoundException("No orders found for the given date.");
	        }
	        log.info("Order filtered based on "+date);
	        return orders;
		
	}

	
	public List<Orders> findByDateBefore(LocalDate date) throws OrderNotFoundException {
			List<Orders> filterByOrderBefore = orderRepo.findByDateBefore(date);
			 if (filterByOrderBefore.isEmpty()) {
				 log.error("Order not found with "+date);
		            throw new OrderNotFoundException("No orders found.");
		        }
			 log.info("Order filtered");
		        return filterByOrderBefore;
	}
	
	
	 public List<Orders> findByDateBetween(LocalDate startDate, LocalDate endDate) throws OrderNotFoundException {
	         List<Orders> byOrderDateBetween = orderRepo.findByDateBetween(startDate, endDate);
	         if(byOrderDateBetween.isEmpty()) {
	        	 log.error("Order not found");
	        	 throw new OrderNotFoundException("No orders found.");
	         }
	        	 log.info("Order filtered between "+ startDate +" and "+endDate );
	        	 return byOrderDateBetween;
	    }


	 public List<Orders> findByTotalPrice(double price) throws OrderNotFoundException{
		 List<Orders> byTotalPrice = orderRepo.findByTotalPrice(price);
		 if(byTotalPrice.isEmpty()) {
        	 log.error("Order not found with Totalprice: "+price);
        	 throw new OrderNotFoundException("Order not found with Totalprice: "+price);
         }
        	 log.info("Order found with Totalprice: "+price );
        	 return byTotalPrice;
		 
	 }


	@Override
	public Orders updateStatus(int orderId) {
		
		Optional<Orders> byId = orderRepo.findById(orderId);
		Orders orders = byId.get();
		if(byId.isPresent() && orders.getStatus().equals("Ordered")) {
			orders.setStatus("Accepted");
			Orders save = orderRepo.save(orders);
			return save;
		}
		else {
			return null;
			//throw new OrderNotFoundException("");
		}
		
	}
	
	@Override
	public Orders updateStatusByDealer(int orderId) {
		
		Optional<Orders> byId = orderRepo.findById(orderId);
		Orders orders = byId.get();
		if(byId.isPresent()) {
			orders.setStatus("Completed");
			Orders save = orderRepo.save(orders);
			return save;
		}
		else {
			return null;
			//throw new OrderNotFoundException("");
		}
		
	}


	@Override
	public List<Orders> findByDealerName(String name) {
		List<Orders> byDealerName = orderRepo.findByDealerName(name);
		
		if(!byDealerName.isEmpty()) {
			return byDealerName;
		}
		else {
			//throw new OrderNotFoundException("Dealer name not found");
			return null;
		}
	}

}

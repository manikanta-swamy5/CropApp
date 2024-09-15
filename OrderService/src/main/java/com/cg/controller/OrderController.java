package com.cg.controller;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.CropDto;
import com.cg.entity.Orders;
import com.cg.exception.OrderNotFoundException;
import com.cg.service.OrderService;


@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	

	@PostMapping("createOrders/{cropId}")
	@PreAuthorize("hasAnyAuthority('dealer')")
	public ResponseEntity<Orders> createOrder(@PathVariable int cropId, Principal p) throws OrderNotFoundException{
		String name = p.getName();
		Orders order = orderService.createOrder(cropId, name);
		ResponseEntity<Orders> entity = new ResponseEntity<>(order, HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("getOrder/{orderId}")
	@PreAuthorize("hasAnyAuthority('dealer')")
	public ResponseEntity<Orders> getOrder(@PathVariable int orderId) throws OrderNotFoundException {
		Orders order = orderService.getOrder(orderId);
		ResponseEntity<Orders> entity = new ResponseEntity<>(order, HttpStatus.OK);
		return entity;
	}
	

	@DeleteMapping("deleteOrder/{orderId}")
	@PreAuthorize("hasAnyAuthority('admin','dealer')")
	public ResponseEntity<String> deleteOrder(@PathVariable int orderId) throws OrderNotFoundException {
		String deleteOrder = orderService.deleteOrder(orderId);
		ResponseEntity<String> entity = new ResponseEntity<>(deleteOrder, HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping("/filterByDate/{date}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<List<Orders>> findByDate(@PathVariable LocalDate date) throws OrderNotFoundException {
		List<Orders> filterByDate = orderService.findByDate(date);
		ResponseEntity<List<Orders>> entity = new ResponseEntity<>(filterByDate, HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/filterByOrderBefore/{date}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<List<Orders>> findByDateBefore(@PathVariable LocalDate date) throws OrderNotFoundException {
		List<Orders> filterByDate = orderService.findByDateBefore(date);
		ResponseEntity<List<Orders>> entity = new ResponseEntity<>(filterByDate, HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping("/findByDateBetween/{startDate}/{endDate}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<List<Orders>> findByDateBetween(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate) throws OrderNotFoundException {
		List<Orders> filterByDate = orderService.findByDateBetween(startDate,endDate);
		ResponseEntity<List<Orders>> entity = new ResponseEntity<>(filterByDate, HttpStatus.OK);
		return entity;
	}

	
	@GetMapping("findByTotalPrice/{price}")
	@PreAuthorize("hasAnyAuthority('admin','dealer')")
	 public ResponseEntity<List<Orders>> findByTotalPrice(@PathVariable double price) throws OrderNotFoundException{
		 List<Orders> filterByDate = orderService.findByTotalPrice(price);
			ResponseEntity<List<Orders>> entity = new ResponseEntity<>(filterByDate, HttpStatus.OK);
			return entity;
	 }
	
	
	@PutMapping("/updateStatus/{orderId}")
	@PreAuthorize("hasAnyAuthority('farmer')")
	public ResponseEntity<Orders> updateStatus(@PathVariable int orderId) {
		Orders updateStatus = orderService.updateStatus(orderId);
		ResponseEntity<Orders> entity = new ResponseEntity<>(updateStatus, HttpStatus.OK);
		return entity;
	}
	
	@PutMapping("/updateStatusbydealer/{id}")
	@PreAuthorize("hasAnyAuthority('dealer')")
	public ResponseEntity<Orders> updateStatusByDealer(@PathVariable int id) {
		Orders updateStatus = orderService.updateStatusByDealer(id);
		ResponseEntity<Orders> entity = new ResponseEntity<>(updateStatus, HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/getOrdersByName")
	@PreAuthorize("hasAnyAuthority('admin', 'dealer')")
	public ResponseEntity<List<Orders>> getOrdersByName(Principal principal) {
		String name = principal.getName();
		List<Orders> byDealerName = orderService.findByDealerName(name);
		ResponseEntity<List<Orders>> entity = new ResponseEntity<>(byDealerName, HttpStatus.OK);
		return entity;
	}
	
}

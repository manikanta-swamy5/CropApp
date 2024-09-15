package com.cg.secure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cg.entity.OrderDTO;

import feign.Headers;

@Service
@FeignClient(name = "orderservice")
public interface OrderFeignClient {

	@GetMapping("/getOrder/{orderId}")
	@Headers("Authorization: Bearer {token}")
	public OrderDTO getOrderDetails(@PathVariable int orderId);

	
	@PutMapping("/updateStatusbydealer/{id}")
	@Headers("Authorization: Bearer {token}")
	public ResponseEntity<OrderDTO> updateStatusByDealer(@PathVariable int id);
}

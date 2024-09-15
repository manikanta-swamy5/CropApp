package com.cg.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.entity.CropMapping;

import feign.Headers;


//@Service
//@FeignClient(name = "ProductService")
//public interface OrderClient {
//	
//	@PutMapping("/saveCropDto")
//	@Headers("Authorization: Bearer {token}")
//	public CropMapping saveCropDetails(@PathVariable int id,@PathVariable int cropId,@PathVariable int quantity,@PathVariable double price,@PathVariable String location);
//}

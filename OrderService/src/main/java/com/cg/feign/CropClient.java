package com.cg.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.entity.CropDto;

import feign.Headers;

@Service
@FeignClient(name = "cropservice")
public interface CropClient {
	
	
	@GetMapping("/crop/displaycropbyid/{id}")
	@Headers("Authorization: Bearer {token}")
	public CropDto getCropById(@PathVariable int id);
	
	
}

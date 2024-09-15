package com.cg.secure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import feign.Headers;

@Service
@FeignClient(name = "bankservice")
public interface BankFeignClient {

	@PutMapping("/addamount/{accNo}/{balance}")
	@Headers("Authorization: Bearer {token}")
	public ResponseEntity<String> addAmount(@PathVariable long accNo,@PathVariable double balance);
}

package com.cg.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Bank;
import com.cg.exception.BankNotFoundException;
import com.cg.service.BankService;

@RestController
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@PostMapping("/createaccount/{accNo}/{bankName}/{ifscCode}/{balance}")
	@PreAuthorize("hasAnyAuthority('farmer')")
	public ResponseEntity<Bank> createAccount(Principal principal,@PathVariable long accNo,@PathVariable String bankName, 
			@PathVariable String ifscCode,@PathVariable double balance) {
		String name = principal.getName();
		Bank account = bankService.createAccount(name, accNo, bankName, ifscCode, balance);
		ResponseEntity<Bank> entity = new ResponseEntity<>(account, HttpStatus.ACCEPTED);
		return entity;
	}


	@PutMapping("/addamount/{accNo}/{balance}")
	@PreAuthorize("hasAnyAuthority('farmer','dealer')")
	public ResponseEntity<String> addAmount(@PathVariable long accNo,@PathVariable double balance) throws BankNotFoundException {
		String amount = bankService.addAmount(accNo, balance);
		ResponseEntity<String> entity = new ResponseEntity<>(amount, HttpStatus.OK);
		return entity;
	}

	@DeleteMapping("/deleteaccount/{accNo}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<String> deleteAccount(long accNo) throws BankNotFoundException {
		String deleteAccount = bankService.deleteAccount(accNo);
		ResponseEntity<String> entity = new ResponseEntity<>(deleteAccount, HttpStatus.OK);
		return entity;
		
	}


	@PutMapping("/updateaccdetails/{accNo}/{bankName}/{ifscCode}")
	@PreAuthorize("hasAnyAuthority('farmer')")
	public ResponseEntity<Bank> updateAccDetails(Principal pricipal,@PathVariable long accNo,
			@PathVariable String bankName,@PathVariable String ifscCode) throws BankNotFoundException {
		String name = pricipal.getName();
		Bank updateAccDetails = bankService.updateAccDetails(name, accNo, bankName, ifscCode);
		ResponseEntity<Bank> entity = new ResponseEntity<>(updateAccDetails, HttpStatus.OK);
		return entity;
		
	}
	
	@GetMapping("/viewbalance")
	@PreAuthorize("hasAnyAuthority('farmer')")
	public ResponseEntity<Double> viewBalance(Principal principal) throws BankNotFoundException {
		String name = principal.getName();
		double viewBalance = bankService.viewBalance(name);
		ResponseEntity<Double> entity = new ResponseEntity<>(viewBalance, HttpStatus.OK);
		return entity;
	}

	
}

package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Bank;

public interface BankServiceRepository extends JpaRepository<Bank, String>{
	
	public Bank findByBankAccNumber(long accNumber);
	
}

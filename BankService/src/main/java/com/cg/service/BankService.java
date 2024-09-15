package com.cg.service;

import com.cg.entity.Bank;
import com.cg.exception.BankNotFoundException;

public interface BankService {

	public Bank createAccount(String name, long accNo, String bankName, String ifscCode, double balance);
	
	public Bank updateAccDetails(String name, long accNo, String bankName,String ifscCode) throws BankNotFoundException;
	
	public String addAmount(long accNo, double amount) throws BankNotFoundException;
	
	public String deleteAccount(long accNo) throws BankNotFoundException;
	
	public double viewBalance(String name) throws BankNotFoundException;
}

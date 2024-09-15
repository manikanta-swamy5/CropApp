package com.cg.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Bank;
import com.cg.exception.BankNotFoundException;
import com.cg.repository.BankServiceRepository;

import lombok.extern.slf4j.Slf4j;


@Service
public class BankServiceImpl implements BankService{
	
	Logger log = LoggerFactory.getLogger(BankServiceImpl.class);
	
	@Autowired
	BankServiceRepository bankServiceRepository;


	@Override
	public Bank createAccount(String name, long accNo, String bankName, String ifscCode, double balance) {
		log.info("Bank account created successfully");
		Bank bank = new Bank();
		bank.setUserName(name);
		bank.setBankAccNumber(accNo);
		bank.setBankName(bankName);
		bank.setIfscCode(ifscCode);
		bank.setBalance(balance);
		
		Bank banks = bankServiceRepository.save(bank);
		return banks;
	}


	@Override
	public String addAmount(long accNo, double amount) throws BankNotFoundException {
		Bank byBankAccNumber = bankServiceRepository.findByBankAccNumber(accNo);
		if(byBankAccNumber != null) {
			byBankAccNumber.setBalance(byBankAccNumber.getBalance() + amount);
			Bank save = bankServiceRepository.save(byBankAccNumber);
			log.info("Money added successfully into account");
			return amount + " added successfully";
		}
		else {
			log.error("Account not found");
			throw new BankNotFoundException("No account Found");
		}
		
	}

	@Override
	public String deleteAccount(long accNo) throws BankNotFoundException {
		 Bank BankAccNumber = bankServiceRepository.findByBankAccNumber(accNo);
		if(BankAccNumber != null) {
			bankServiceRepository.deleteById(BankAccNumber.getBankName());
			log.info("Bank account deleted successfully");
			return "Bank Account Deleted successfully";
		}
		else {
			log.error("No bank account not found with this username");
			throw new BankNotFoundException("No Bank Account Found With UserName");
		}	
	}

	@Override
	public Bank updateAccDetails(String name, long accNo, String bankName, String ifscCode) throws BankNotFoundException {
		Optional<Bank> byId = bankServiceRepository.findById(name);
		if(byId.isPresent()) {
			Bank bank = byId.get();
			bank.setBankAccNumber(accNo);
			bank.setBankName(bankName);
			bank.setIfscCode(ifscCode);
			
			Bank banks = bankServiceRepository.save(bank);
			log.info("Bank details updated successfully");
			return banks;
		}
		else {
			log.error("Bank account not found");
			throw new BankNotFoundException("No Bank Account Found");
		}
		
	}


	@Override
	public double viewBalance(String name) throws BankNotFoundException {
		Optional<Bank> byId = bankServiceRepository.findById(name);
		if(byId.isPresent()) {
			Bank bank = byId.get();
			double balance = bank.getBalance();
			log.info("Balance viewed");
			return balance;
		}
		else {
			log.error("Account not found");
			throw new BankNotFoundException("No Bank Account Found With Username");
		}
	}

}

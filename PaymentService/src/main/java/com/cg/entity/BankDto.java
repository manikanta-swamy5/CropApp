package com.cg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {
	//@Id
	private String userName;
	//@Column(unique = true, nullable = false)
	private long bankAccNumber;
	private String bankName;
	private String ifscCode;
	private double balance;
}

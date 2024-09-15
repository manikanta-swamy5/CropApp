package com.cg.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	//@NotBlank(message = "Dealer Name can't be empty")
	//@Size(min = 5,max=30,message = "Dealer name must contain only alphabetic characters")
	//@Pattern(regexp = "[a-zA-Z\\ ]{5,30}")
	private String dealerName;//TOKEN
	
	@NotNull(message = "cropId can't be empty")
	private int cropId;
	
	@Min(value = 1,message = "Select valid quantity")
	@NotNull(message = "Quantity can be empty")
	private int quantity;
	
	@NotNull(message = "price can't be empty")
	@Min(value = 1,message = "Price should be positive")
	private double totalPrice;
	
	@Pattern(regexp = "[a-zA-Z\\ ]+" , message = "status should only contain alphabets")
	private String status;
	
	@NotNull(message = "Order date can't be null")
	@PastOrPresent(message = "Order date must be in the past or present")
	private LocalDate date;

	
	
}

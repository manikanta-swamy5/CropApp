package com.cg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cropmapping")
public class CropMapping {
	
	@Id
	private int id;
	
	private int cropId;
	
	@NotNull(message = "Quantity cannot be null")
	@Min(value = 1,message = "Quantity cannot be less than zero")
	@Column(name = "quantity_in_tonnes")
	private int quantity;
	
	@Positive(message = "Price cannot be Negative")
	private double price;
	
//	@NotNull(message = "Farmer ID cannot be null")
//	@Min(value = 1,message = "Farmer ID cannot be Negative")
	private String farmerName;
	
//	private String measurement;
	
	@NotBlank(message = "Location cannot be empty")
	@Size(max = 100, message = "Location cannot be more than 100 characters")
	private String location;
}

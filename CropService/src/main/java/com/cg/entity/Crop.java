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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Crop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crop {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cropId;
	
	@NotBlank(message = "Crop name cannot be empty")
	@Size(min = 3,max = 50,message = "CropName must between 3 and 50 letters and can only contain alphabetic characters")
	@Pattern(regexp = "[a-zA-Z\\ ]{3,50}")
	private String cropName;
	
	
		

}

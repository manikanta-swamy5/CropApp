package com.cg.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "userlogin")
public class UserProfile {

	@Id
	private int profileId;
	@Column(nullable = false, unique = true)
	private String username;
	private String emailId;
	private long mblNumber;
	private String role;
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="profileId")
    private List<Address> addresses;
  	
	
	
}

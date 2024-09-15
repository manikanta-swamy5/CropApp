package com.cg.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.UserProfile;
import com.cg.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/profileController")
public class UserController {

	@Autowired
	UserService userService;
	
	@PutMapping("/updatedetails/{email}/{mblNumber}")
	@PreAuthorize("hasAnyAuthority('admin','farmer','dealer')")
	public ResponseEntity<UserProfile> updateDetails(Principal principal,@PathVariable String email,
			@PathVariable long mblNumber) {
		String name = principal.getName();
		UserProfile updateDetails = userService.updateDetails(name, email, mblNumber);
		return new ResponseEntity<UserProfile>(updateDetails, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<?> deleteByUsername(Principal principal) {
		String userName = principal.getName();
		userService.deleteByUsername(userName);
		return new ResponseEntity<String>("user is deleted", HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	@PreAuthorize("hasAnyAuthority('admin')")
	public List<UserProfile> getAllUsers() {
		List<UserProfile> all = userService.getAllUsers();
		return all;
	}
	
}

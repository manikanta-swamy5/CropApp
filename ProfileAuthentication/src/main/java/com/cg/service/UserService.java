package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entity.UserProfile;

@Service
public interface UserService {

	String deleteByUsername(String username);

	UserProfile updateDetails(String name, String email, long mblNumber);
	
	List<UserProfile> getAllUsers();
}

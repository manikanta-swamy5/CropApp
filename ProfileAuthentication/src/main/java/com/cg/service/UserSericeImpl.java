package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.UserProfile;
import com.cg.repository.UserProfileRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserSericeImpl implements  UserService{

	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Override
	public String deleteByUsername(String username) {
		userProfileRepository.deleteByUsername(username);
		return "user is deleted";
		
	}

	@Override
	public UserProfile updateDetails(String name, String email, long mblNumber) {
		UserProfile userProfile = userProfileRepository.findByUsername(name);
		if(userProfile != null) {
			userProfile.setEmailId(email);
			userProfile.setMblNumber(mblNumber);
			
			UserProfile users = userProfileRepository.save(userProfile);
			return users;
		}
		else {
			throw new RuntimeException("No User Found with name");
		}
		
	}

	@Override
	public List<UserProfile> getAllUsers() {
		List<UserProfile> all = userProfileRepository.findAll();
		return all;
	}

}

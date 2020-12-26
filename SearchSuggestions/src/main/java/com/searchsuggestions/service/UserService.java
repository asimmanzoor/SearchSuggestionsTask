package com.searchsuggestions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchsuggestions.hibernate.mapping.User;
import com.searchsuggestions.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User findByCity(String city) {
		return userRepository.findFirstByAddressCity(city);
		// TODO Auto-generated method stub
		 
	}
	
	public List<User> findByUserCity(String city) {
		return userRepository.findUsersByCity(city);
	}
	
}

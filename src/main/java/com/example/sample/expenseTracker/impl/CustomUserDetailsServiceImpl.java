
package com.example.sample.expenseTracker.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sample.expenseTracker.model.CustomUserDetails;
import com.example.sample.expenseTracker.model.User;
import com.example.sample.expenseTracker.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("find user by name " + username);
		User user = userRepository.findByName(username);
		System.out.println("Password for name [" + username + "] is [" + user.getPasswordHash() + "]");
		CustomUserDetails userDetails = new CustomUserDetails();
		userDetails.setUser(user);
		System.out.println(userDetails.toString());
		return userDetails;
	}

}

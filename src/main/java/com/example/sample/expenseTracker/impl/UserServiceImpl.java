package com.example.sample.expenseTracker.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.expenseTracker.exception.ApplicationException;
import com.example.sample.expenseTracker.model.User;
import com.example.sample.expenseTracker.repository.UserRepository;
import com.example.sample.expenseTracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User entity) {
		Optional<User> optExisting = userRepository.findById(entity.getId());
		if(optExisting.isPresent()) {
			User existing = optExisting.get();
			entity.setSalt(existing.getSalt());
			entity.setPasswordHash(existing.getPasswordHash());
		}else {
			entity.setSalt(entity.getName());
			entity.setPasswordHash(entity.getUserCode()+entity.getPasswordHash());
		}
		return userRepository.save(entity);
	}

	@Override
	public Boolean deleteById(Long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}else {
			return false;
		}

	}
	@Override
	public User findUserByToken(String token) {
		return userRepository.findByPasswordHash(token);
		
	}

}

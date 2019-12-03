package com.sreekanth.springit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sreekanth.springit.domain.User;
import com.sreekanth.springit.repository.UserRepository;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private final RoleService roleService;
	private final UserRepository userRepository;

	public UserService(RoleService roleService, UserRepository userRepository) {
		this.roleService = roleService;
		this.userRepository = userRepository;
	}

	public User register(User user) {
		return user;
	}
	
	public User save (User user) {
		return userRepository.save(user);
	}

}

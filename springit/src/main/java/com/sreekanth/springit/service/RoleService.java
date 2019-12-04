package com.sreekanth.springit.service;

import org.springframework.stereotype.Service;

import com.sreekanth.springit.domain.Role;
import com.sreekanth.springit.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}

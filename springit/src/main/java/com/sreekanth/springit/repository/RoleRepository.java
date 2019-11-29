package com.sreekanth.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.springit.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

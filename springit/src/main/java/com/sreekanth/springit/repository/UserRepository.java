package com.sreekanth.springit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.springit.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
	 Optional<User> findByEmail(String email);
	 Optional<User> findByEmailAndActivationCode(String email, String activationCode);
}

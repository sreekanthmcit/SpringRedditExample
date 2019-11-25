package com.sreekanth.springit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.springit.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
	
	List<Link> findByTitle(String title);
	

}

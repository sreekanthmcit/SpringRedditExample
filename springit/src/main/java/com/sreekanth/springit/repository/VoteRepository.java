package com.sreekanth.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.springit.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{

}

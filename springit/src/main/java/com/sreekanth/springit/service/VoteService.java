package com.sreekanth.springit.service;

import org.springframework.stereotype.Service;

import com.sreekanth.springit.domain.Vote;
import com.sreekanth.springit.repository.VoteRepository;

@Service
public class VoteService {
	
	private VoteRepository voteRepository;

	public VoteService(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}
	
	public Vote save(Vote vote) {
		return voteRepository.save(vote);
	}

}

package com.sreekanth.springit.controller;

import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sreekanth.springit.domain.Link;
import com.sreekanth.springit.domain.Vote;
import com.sreekanth.springit.repository.LinkRepository;
import com.sreekanth.springit.repository.VoteRepository;

@RestController
public class VoteController {
	
	private VoteRepository voteRepository;
	private LinkRepository linkRepository;
	
	public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
		this.voteRepository = voteRepository;
		this.linkRepository = linkRepository;
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
	public int vote(@PathVariable Long linkID,@PathVariable short direction,@PathVariable int voteCount){
		Optional<Link> optionalLink = linkRepository.findById(linkID);
		if(optionalLink.isPresent()) {
			Link link = optionalLink.get();
			Vote vote = new Vote(direction,link);
			voteRepository.save(vote);
			int updateVoteCount = voteCount+direction;
			link.setVoteCount(updateVoteCount);
			linkRepository.save(link);
			return updateVoteCount;
		}
		
		return voteCount;
	}
	
	

}

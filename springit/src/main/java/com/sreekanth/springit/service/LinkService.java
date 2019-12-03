package com.sreekanth.springit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sreekanth.springit.domain.Link;
import com.sreekanth.springit.repository.LinkRepository;

@Service
public class LinkService {
	
	private LinkRepository linkRepository;

	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}
	
	public List<Link> findAll(){
		return	linkRepository.findAll();
	}
	
	public Optional<Link> findById(Long id){
		return linkRepository.findById(id);
	}
	
	public Link save(Link link) {
		return linkRepository.save(link);
	}

}

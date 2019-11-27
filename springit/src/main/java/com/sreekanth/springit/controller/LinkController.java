package com.sreekanth.springit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sreekanth.springit.domain.Link;
import com.sreekanth.springit.repository.LinkRepository;

@RestController
@RequestMapping("/links")
public class LinkController {
	
	
	private LinkRepository linkRepository;

	@Autowired
    public LinkController(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	@GetMapping("/")
    public List<Link> list() {
        return linkRepository.findAll();
    }

    @PostMapping("/create")
    public Link create(@ModelAttribute Link link) {
       return linkRepository.save(link);
    }
    
    @GetMapping("/{id}")
    public Optional<Link> read(@PathVariable Long id) {
        return linkRepository.findById(id);
    }
    
    @PutMapping("/{id}")
    public Link update(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }
    
    @DeleteMapping("/delete")
    public void delete(@PathVariable Long id) {
    	linkRepository.deleteById(id);
    }
}
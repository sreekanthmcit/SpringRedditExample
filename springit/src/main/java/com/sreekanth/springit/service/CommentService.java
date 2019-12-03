package com.sreekanth.springit.service;

import org.springframework.stereotype.Service;

import com.sreekanth.springit.domain.Comment;
import com.sreekanth.springit.repository.CommentRepository;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

}

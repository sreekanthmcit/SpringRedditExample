package com.sreekanth.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.springit.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

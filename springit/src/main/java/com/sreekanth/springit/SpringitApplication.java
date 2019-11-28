package com.sreekanth.springit;

import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sreekanth.springit.config.SpringitProperties;
import com.sreekanth.springit.domain.Comment;
import com.sreekanth.springit.domain.Link;
import com.sreekanth.springit.repository.CommentRepository;
import com.sreekanth.springit.repository.LinkRepository;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperties.class)
@EnableJpaAuditing
public class SpringitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
		System.out.println("Welcome to Spring Boot 2");
	}
	
	//@Bean
	CommandLineRunner runner(LinkRepository linkrepository,CommentRepository commentRepository) {
		return args ->{
			Link link = new Link("Spring Boot 2", "http://start.spring.io");
			linkrepository.save(link);
			Comment comment = new Comment("This learning is great",link);
			commentRepository.save(comment);
			link.addComment(comment);
			
			
			Link firstLink = linkrepository.findByTitle("Spring Boot 2");
			System.out.println(firstLink.getUrl());
			
			
			
		};
	}
	
	@Bean
	PrettyTime prettyTime() {
		return new PrettyTime();
	}

}

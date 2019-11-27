package com.sreekanth.springit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@RequestMapping(path = "/",method = RequestMethod.GET)
	public String getHome() {
		return "Hello welcome to Spring Boot Rest Controller";
	}
}

package com.sreekanth.springit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	/*
	 * @RequestMapping(path = "/",method = RequestMethod.GET) public String
	 * getHome() { return "Hello welcome to Spring Boot Rest Controller"; }
	 */
	
	  @GetMapping("/home")
	    public String home(Model model) {
	        model.addAttribute("title","Hello, Thymeleaf!");
	        return "home";
	    }
}

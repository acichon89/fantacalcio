package com.javangarda.fantacalcio.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javangarda.fantacalcio.football.domain.repositories.ClubRepository;

@Controller
public class HelloWorldController {

	@Autowired
	private ClubRepository repo;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String hello(Model model){
		model.addAttribute("repoName", repo.getClass().getName());
		return "home";
	}
}

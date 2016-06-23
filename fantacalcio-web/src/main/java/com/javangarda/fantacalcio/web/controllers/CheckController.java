package com.javangarda.fantacalcio.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckController {

	@RequestMapping(value="/check", method=RequestMethod.GET)
	public String check(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", auth.getName());
		return "check";
	}
	
	@RequestMapping(value="principal", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Object getPrincipal(){
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}

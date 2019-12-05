package com.cgi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
		
	@RequestMapping("/register")
	public ModelAndView goRegister() {
		return new ModelAndView("register");
	}
	
	
	
}




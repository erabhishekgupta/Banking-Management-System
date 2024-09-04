package com.cetpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("central-bank")
public class homeController {

	
	@RequestMapping("home")
	public String getHomeView(HttpSession ses) {
		
		if(ses.getAttribute("name")==null) {
			return "redirect:/central-bank/login" ;
		}
		return "Home/homepage.html";
		
		
		
	}
}

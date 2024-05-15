package com.example.scoremanage.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
 
public class LoginController {
 
	@GetMapping("/")
	public String getLogin(Model model) {
		return "login";
	}
	@GetMapping("/logoutsuccess")
	public String getLogout(Model model) {
		return "logout";
	}

 
	
 
}
package com.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Lazy
@Controller
public class HtmlController {
	@GetMapping("/login")
	public String login() {
		return  "login2";
	}
	@GetMapping("/home")
	public  String home(String username,String password) {
		return  "home";
	}
	@GetMapping("/swagger")
	public  String swagger(String username,String password) {
		return  "home";
	}
	
	
}

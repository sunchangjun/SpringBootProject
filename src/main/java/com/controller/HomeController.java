package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.result.Result;

@RestController
public class HomeController {
	

	
	@GetMapping("/hello")
	public Result hello() {
		return  Result.setRetDate("1", "1", "hello");
	}
	@GetMapping("/user")
	public Result user() {
		return  Result.setRetDate("1", "1", "user");
	}
	@GetMapping("/adv")
	public Result adv() {
		return  Result.setRetDate("1", "1", "adv");
	}
	@GetMapping("/actitv")
	public Result actitv() {
		return  Result.setRetDate("1", "1", "actitv");
	}
	
	
	
	
}

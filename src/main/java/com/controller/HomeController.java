package com.controller;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.result.Result;
@Lazy   //延迟加载注解,用于ben上,调用时才注入,可加载@autowirit上
@RestController
public class HomeController {
	

	
	@GetMapping("/hello")
	public Result hello(Optional<String> name) {
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

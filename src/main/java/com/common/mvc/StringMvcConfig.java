package com.common.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@EnableWebMvc  
@Configuration
public class StringMvcConfig extends WebMvcConfigurerAdapter{
	
	  //  新建WebMvcConfig类并继承WebSecurityConfigurerAdapter，WebSecurityConfigurerAdapter是security提供用于更改默认配置，实现configure方法
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }


}

package com.common.mvc;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class StringMvcConfig extends WebMvcConfigurerAdapter{
	
	  //  新建WebMvcConfig类并继承WebSecurityConfigurerAdapter，WebSecurityConfigurerAdapter是security提供用于更改默认配置，实现configure方法
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/home").setViewName("home");
    }


}

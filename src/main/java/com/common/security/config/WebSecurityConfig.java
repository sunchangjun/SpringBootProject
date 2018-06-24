package com.common.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.common.aop.Aop;
import com.common.security.service.MyFilterSecurityInterceptor;
import com.common.security.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity /* 通过@EnableWebSecurity注解开启Spring Security的功能 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	@Bean
	UserDetailsService myUserDetailsService() { // 注册UserDetailsService 的bean
		return new MyUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		log.info("调用SecurityConfig .configure(HttpSecurity http)");
		
		http.authorizeRequests().antMatchers("/login").permitAll().
		and()
				.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		log.info("WebSecurityConfig.configureGlobal(AuthenticationManagerBuilder auth)");
		auth.userDetailsService(myUserDetailsService());
	}

}

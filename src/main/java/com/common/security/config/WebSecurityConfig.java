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
import com.common.security.service.MyFilterSecurityInterceptor;
import com.common.security.service.CustomUserService;


@Configuration
//@EnableWebSecurity /* 通过@EnableWebSecurity注解开启Spring Security的功能 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	@Bean
	UserDetailsService customUserService() { // 注册UserDetailsService 的bean
		return new CustomUserService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		log.info("调用SecurityConfig .configure(HttpSecurity http)");
		
		http.authorizeRequests().antMatchers("/home","/login").permitAll().anyRequest().authenticated(). 
		and()
				.formLogin().loginPage("/login"). permitAll().
		and().logout().permitAll();
		
		
//		// 验证请求
//		http.authorizeRequests(). // 定义哪些URL需要被保护
//				antMatchers("/hello/**", "/","/login").permitAll(). // 设置所有人都可以访问登录页面
//				antMatchers("/admin/**").hasRole("ADMIN").// 以 "/admin/" 开头的URL只能由拥有 "ROLE_ADMIN"角色的用户访问。
//				antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").// 任何以"/db/" 开头的URL需要用户同时具有
//																					// "ROLE_ADMIN" 和 "ROLE_DBA"
//				anyRequest().authenticated(); // 尚未匹配的任何URL要求用户进行身份验证
//		// and().
//		http.formLogin().loginPage("/login"). // 设置登录页面
//
//		// loginProcessingUrl("/user/login"). // 自定义的登录接口
//		// failureUrl("/login?error").
//				permitAll(); // 定义当需要用户登录时候，转到的登录页面。登录页面用户任意访问
//		http.logout().logoutSuccessUrl("/my/index").// 注销之后跳转的URL。
//				invalidateHttpSession(true).// 指定是否在注销时让HttpSession无效。 默认设置为 true。
//				permitAll(); // 注销行为任意访问
//
//		http.csrf().disable(); // 关闭csrf防护
//		http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("WebSecurityConfig.configureGlobal(AuthenticationManagerBuilder auth)");
		auth.userDetailsService(customUserService());// user Details Service验证
	}

}

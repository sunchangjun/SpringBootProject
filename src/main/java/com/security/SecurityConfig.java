package com.security;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import com.common.uttil.Md5Util;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	   private Logger log = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	@Bean
	UserDetailsService myUserDetailsService() {
		// 注册UserDetailsService 的bean
		return new MyUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("SecurityConfig.configure(auth)");
		auth.userDetailsService(myUserDetailsService()).passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return Md5Util.EncoderByMd5((String) rawPassword, "utf8");
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(Md5Util.EncoderByMd5((String) rawPassword, "utf8"));
			}
		});
	}
	
	 public String[] whiteUrls = new String[]{"/login","/adv","/swageger3/index.html"};
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    
		/*授权*/
	     http .authorizeRequests() 
	     .antMatchers(whiteUrls).permitAll()//任何人都可以访问的接口
//	     .antMatchers("").hasRole("ADMIN") //需要ROLE_ADMIN权限才能访问
        .anyRequest()  .authenticated() //尚未匹配的任何URL要求用户进行身份验证	     
	     
	    /*登录*/
	     .and()
	   //.passwordParameter("a").usernameParameter("b")//如果你前台登陆的form表单登录名和密码不是username,password，那么就配置本行修改你需要的名字
	     .formLogin().loginPage("/login").permitAll()  /*未登录下默认跳转的页面*/
	     .defaultSuccessUrl("/home")/*登录后跳转的页面*/
	     .failureUrl("/login?error").permitAll(); /*登录失败跳转的页面*/
	     /*登出*/
	 	http
		.logout()     //提供注销支持，使用WebSecurityConfigurerAdapter会自动被应用。                                                           
//			.logoutUrl("/my/logout")            //设置触发注销操作的URL (默认是/logout). 如果CSRF内启用（默认是启用的）的话这个请求的方式被限定为POST。                                     
			.logoutSuccessUrl("/login")               //注销之后跳转的URL。默认是/login?logout                                                        
			.invalidateHttpSession(true) ;           //	指定是否在注销时让HttpSession无效。 默认设置为 true。                                 
		
	     
	     
	     
	     
	}
	
	
	
	

}

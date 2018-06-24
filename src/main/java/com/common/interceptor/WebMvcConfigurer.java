package com.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 编写拦截器配置文件主类 WebMvcConfigurer 此类必须继承 WebMvcConfigurerAdapter 类，并重写其中的方法
 * addInterceptors 并且在主类上加上注解 @Configuration
 * 
 */
//@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Bean
	public MyInterceptor getInterfaceAuthCheckInterceptor() {
		return new MyInterceptor();
	}

	/**
	 * 
	 * Description:重写增加自定义拦截器的注册，某一个拦截器需要先注册进来，才能工作 拦截器的执行是会根据 registry
	 * 注入的先后顺序执行，比如：/one/** 同时被 OneInterceptor、TwoInterceptor 拦截， 但会先执行
	 * OneInterceptor拦截的业务请求，因为它先注入进来的
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(getInterfaceAuthCheckInterceptor()).addPathPatterns("/home");//.excludePathPatterns("");

//		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/one/**").addPathPatterns("/two/**");

		super.addInterceptors(registry);
	}

	/**
	 * 其中要注意注册时的区别
	 * 
	 * registry.addInterceptor(getInterfaceAuthCheckInterceptor()).addPathPatterns("/api/**");
	 * 这种方式无论什么情况都可以
	 * 
	 * 
	 * registry.addInterceptor(newInterfaceAuthCheckInterceptor()).addPathPatterns("/api/**");这种情况时，
	 * 自定义的interceptor中不能注入其他内容，比如redis或者其他service，如果要注入，必须使用上面这种方法
	 */
}

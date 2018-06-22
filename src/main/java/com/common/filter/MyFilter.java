package com.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
//注解方式配置拦截器

/**
 * @Component就是把这个类注入到IOC容器中
 * @WebFilter(urlPatterns = {"/Blogs",""},filterName =
 *                        "blosTest")说明这是一个web过滤器，它拦截的url为/Blogs，过滤器名字为blogsTest
 */
@Order(1)
@Component
@WebFilter(urlPatterns = {"/home","/hello"}, filterName = "过滤器")
public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		/**
		 * 初始化（init）和摧毁（destroy）方法一般不会用到，具体使用看下源码便知 doFilter（）是过滤器的核心
		 * 注意：在实现接口方法之后，我们要转换request和response类型至HttpServlet，否则接下去的操作可能会报错。
		 * 如果过滤通过，执行filterChain.doFilter(request,response); 说明这个url已经经过了我们的Filter
		 */
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		System.out.println("过滤器实现__"+request.getRequestURL());
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	


}

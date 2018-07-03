package com.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

/**
 * 服务器启动,首先加载过滤器链,调用doFilter 然后,
 * 加载此类,注入FilterInvocationSecurityMetadataSource接口,
 * 所有此接口需要重写,都用重写的getAttributes()方法
 * 
 * @author Administrator
 *
 */
@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	
	private static Logger log = LoggerFactory.getLogger(MyFilterSecurityInterceptor.class);
	
	@Autowired
    private FilterInvocationSecurityMetadataSource sms;
	
	@Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
		/**
		 * 必须重写此方法注入AccessDecisionManager
		 * init方法的调用失败; 嵌套异常是java.lang.IllegalArgumentException：需要AccessDecisionManager
		 */
        super.setAccessDecisionManager(myAccessDecisionManager);
    }



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);

	}

	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		/*
		 * fi里面有一个被拦截的url 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object
		 * object)这个方法获取fi对应的所有权限 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
		 * 
		 */
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			// 执行下一个拦截器
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getSecureObjectClass() {
	/**
	 * 调用init,此处必须提供非空响应
	 */
        return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.sms;
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
}

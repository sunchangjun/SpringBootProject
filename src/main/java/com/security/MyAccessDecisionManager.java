package com.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager{
	
	private static Logger log = LoggerFactory.getLogger(MyAccessDecisionManager.class);

	  /* decide 方法是判定是否拥有权限的决策方法，
	  authentication 是释CustomUserService中循环添加到 GrantedAuthority 对象中的权限信息集合.
	  object 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
	  configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
	  此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。
	  如果不在权限表中则放行。
	*/
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)throws AccessDeniedException, InsufficientAuthenticationException {
		log.info("调用AccessDecisionManager.decide()方法");
		if(null== configAttributes || configAttributes.size() <=0) {
	            return;
	        }
		   
			Iterator<ConfigAttribute> ite = configAttributes.iterator();
			while (ite.hasNext()) {
				ConfigAttribute ca = ite.next();
				String needRole = ((SecurityConfig) ca).getAttribute();
				// ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限.
				for (GrantedAuthority ga : authentication.getAuthorities()) {
					log.info("需要的权限:needRole={}",needRole);
					log.info("用户循环添加的权限={}",ga.getAuthority());
					if (needRole.trim().equals(ga.getAuthority().trim())) {					
						return;
					}
				}
			}
			
			throw new AccessDeniedException("权限不足");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		/**
		 * 必须返回true:否则异常AccessDecisionManager does not support secure object class: class org.springframework.security.web.FilterInvocation
		 */
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		/**
		 * 必须返回true:否则异常AccessDecisionManager does not support secure object class: class org.springframework.security.web.FilterInvocation
		 */
		return true;
	}
	

}

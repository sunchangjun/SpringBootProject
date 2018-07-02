package com.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.DigestUtils;

import boss.portal.service.impl.GrantedAuthorityImpl;


/**
 * 自定义身份认证组件
 * @author Administrator
 *
 */
public class myAuthenticationProvider implements AuthenticationProvider{
	private static Logger log = LoggerFactory.getLogger(myAuthenticationProvider.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("myAuthenticationProvider.authenticate()");
		   // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 认证逻辑
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        
        if (null != userDetails) {
        	String encodePassword = DigestUtils.md5DigestAsHex((password).getBytes());
        	 if (userDetails.getPassword().equals(encodePassword)) {
        		  // 这里设置权限和角色
                 ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                 authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN"));
                 authorities.add( new GrantedAuthorityImpl("AUTH_WRITE"));
                 // 生成令牌 这里令牌里面存入了:name,password,authorities, 当然你也可以放其他内容
                 Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
                 return auth;
        	 }else {
        		 throw new BadCredentialsException("密码错误");
        	 }
        		 
        	
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
        
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}

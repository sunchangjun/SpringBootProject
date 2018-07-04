package com.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dao.PermissionRepository;
import com.dao.RoleRepository;
import com.dao.UserRepository;
import com.entity.po.Permission;
import com.entity.po.SysRole;
import com.entity.po.SysUser;


@Component
public class MyUserDetailsService implements UserDetailsService{
	private static Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PermissionRepository  permissionRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("调用loadUserByUsername()"+username);
		SysUser user=userRepository.findByUserName(username);
		
		//
		if(null == user) {
			   throw new UsernameNotFoundException("admin: " + username + " 用户不存在!");
		}
		List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
		List<SysRole> roleList=roleRepository.getRoleListByUserId(user.getUserId());
		for (SysRole sysRole : roleList) {
			grantedAuthorities.add(new SimpleGrantedAuthority(sysRole.getRoleName()));
		}
		 return new User(user.getUserName(), user.getPassWord(), grantedAuthorities);
		
		
	}
	
	

}

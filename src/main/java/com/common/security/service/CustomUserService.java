package com.common.security.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.common.security.config.WebSecurityConfig;
import com.dao.PermissionRepository;
import com.dao.UserRepository;
import com.entity.po.Permission;
import com.entity.po.SysUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
//@Service
@Component
public class CustomUserService implements UserDetailsService {
	private static Logger log = LoggerFactory.getLogger(CustomUserService.class);
	   @Autowired
	   UserRepository userDao;
	    @Autowired
	    PermissionRepository permissionDao;

	    public UserDetails loadUserByUsername(String username) {
	    	log.info("loadUserByUsername():用户验证");
	        SysUser user = userDao.findByUserName(username);
	        if (user != null) {
	            List<Permission> permissions = permissionDao.findByAdminUserId(user.getUserId());
	            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
	            for (Permission permission : permissions) {
	                if (permission != null && permission.getPermissionName()!=null) {

	                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermissionName ());
	                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
	                grantedAuthorities.add(grantedAuthority);
	                }
	            }
	            return new User(user.getUserName(), user.getPassWord(), grantedAuthorities);
	        } else {
	            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
	        }
	    }

}

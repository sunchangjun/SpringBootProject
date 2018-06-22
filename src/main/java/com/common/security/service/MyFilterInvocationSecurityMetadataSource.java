package com.common.security.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import com.alibaba.fastjson.JSONObject;

public class MyFilterInvocationSecurityMetadataSource
		implements org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource {
	// 配置文件的加载
	private static String urlRoleMap;
	private static final Logger LOGGER = LoggerFactory.getLogger(MyFilterInvocationSecurityMetadataSource.class);
	static {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/application.properties");
		LOGGER.error("加载URL的配置文件");
		try {
			prop.load(in);
			urlRoleMap = prop.getProperty("urls").trim();

		} catch (IOException e) {
			LOGGER.error("spring-security.properties配置路径不存在{}", e.getMessage());
			e.printStackTrace();
		}
	}
	private final AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation fi = (FilterInvocation) object;
		StringBuffer roles = new StringBuffer("START");
		String url = fi.getRequestUrl();
		// String httpMethod = fi.getRequest().getMethod();
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.parseObject(urlRoleMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = "";
		// 遍历json
		for (String key : jsonObject.keySet()) {
			if (antPathMatcher.match(key, url)) {
				value = jsonObject.getString(key);
				// 如果有，号的就说明是多个角色
				roles.append("," + value);
			}
		}
		if (!("START").equals(roles)) {
			return SecurityConfig.createList(roles.toString());
		} else {
			LOGGER.error("没有匹配到URL");
			// 没有匹配到
			// return SecurityConfig.createList("NULL");
			throw new AccessDeniedException("not allow");
		}

		// return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}

package com.dao;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.PermissionRepository;
import com.entity.po.Permission;
import com.alibaba.fastjson.JSONObject;
import com.base.BaseJunitTest;
import com.common.redis.service.RedisService;

public class testPermission extends BaseJunitTest {
	
	@Autowired
	PermissionRepository permissionDao;
	
	@Autowired
	RedisService redisService;
	
	@Test
	public  void   test() {
		System.out.println(redisService.exist("sunchangjunn"));
	}
	


	


}

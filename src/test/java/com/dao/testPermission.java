package com.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.PermissionRepository;
import com.alibaba.fastjson.JSONObject;
import com.base.BaseJunitTest;

public class testPermission extends BaseJunitTest {
	@Autowired
	PermissionRepository permissionDao;
	
	@Test
	public  void   test() {
		System.out.println(JSONObject.toJSONString(permissionDao.findByAdminUserId(1)));;
		
	}

}

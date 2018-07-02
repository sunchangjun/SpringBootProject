package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.common.jpa.BaseRepository;
import com.entity.po.SysRole;
import com.entity.po.SysUser;

public interface RoleRepository extends BaseRepository<SysRole, Integer>{
	
	@Query("select sr from SysUser u,SysRoleUser sru,SysRole sr where u.userId=sru.userId and sru.roleId=sr.roleId  where u.userId =?1")
	List<SysRole> getRoleListByUserId(Integer userId);
}

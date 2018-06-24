package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.common.jpa.BaseRepository;
import com.entity.po.SysUser;

public interface UserRepository extends BaseRepository<SysUser, Integer>{
	
	
	@Query("select  u from  SysUser u where u.userName=?1")
	SysUser	findByUserName(String  userName);

}

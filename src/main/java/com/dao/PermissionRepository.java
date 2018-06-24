package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.common.jpa.BaseRepository;
import com.entity.po.Permission;

public interface PermissionRepository extends BaseRepository<Permission, Integer>{
	
	@Query(value="select p.* from sys_User u  LEFT JOIN sys_role_user sru on u.user_id= sru.user_id LEFT JOIN Sys_Role r on sru.role_id=r.role_id    LEFT JOIN sys_permission_role spr on spr.role_id=r.role_id  LEFT JOIN sys_permission p on p.permission_id =spr.permission_id   where u.user_id=?1",nativeQuery=true)
	List<Permission> findByAdminUserId(Integer userId);

}

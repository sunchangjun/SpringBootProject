package com.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "PermissionRole", description = "权限角色对象")
@Entity
@Table(name = "sys_permission_role")
public class PermissionRole implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3337665348885426402L;

	@ApiModelProperty(value = "权限角色id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "permission_role_id")
	private Integer  permissionRoleId;
	
	@ApiModelProperty(value = "角色id")
	@Column(name = "role_id")
    private Integer roleId;
	
	@ApiModelProperty(value = "权限id")
	@Column(name = "permission_id")
	private Integer permissionId;

	public Integer getPermissionRoleId() {
		return permissionRoleId;
	}

	public void setPermissionRoleId(Integer permissionRoleId) {
		this.permissionRoleId = permissionRoleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
	
	

}

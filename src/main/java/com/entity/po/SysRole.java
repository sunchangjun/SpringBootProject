package com.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "SysRole", description = "角色对象")
@Entity
@Table(name = "sys_role")
public class SysRole implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4999972484083528515L;

	@ApiModelProperty(value = "角色id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "role_id")
    private Integer roleId;
	
	@ApiModelProperty(value = "角色名称")
	@Column(name = "role_name", length = 50)
    private String roleName;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	




}

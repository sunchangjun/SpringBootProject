package com.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "SysRoleUser", description = "角色用户对象")
@Entity
@Table(name = "sys_role_user")
public class SysRoleUser implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7763620609896281024L;


	@ApiModelProperty(value = "角色用户id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "role_user_id")
	private Integer roleUserId;
	
	
	@ApiModelProperty(value = "角色id")
	@Column(name = "role_id")
    private Integer roleId;
	
	@ApiModelProperty(value = "用户id")
	@Column(name = "user_id", length = 11)
	private Integer userId;

	public Integer getRoleUserId() {
		return roleUserId;
	}

	public void setRoleUserId(Integer roleUserId) {
		this.roleUserId = roleUserId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	

}

package com.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "Permission", description = "权限对象")
@Entity
@Table(name = "sys_permission")
public class Permission implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 383933365461154220L;
	@ApiModelProperty(value = "权限id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "permission_id")
	private Integer permissionId;
	
	@ApiModelProperty(value = "权限名称")
	@Column(name = "permission_name", length = 50)
	private String permissionName;
	
	@ApiModelProperty(value = "描述")
	@Column(name = "description", length = 50)
	private String description;
	
	@ApiModelProperty(value = "url")
	@Column(name = "url", length = 50)
	private String  url;
	
	@ApiModelProperty(value = "父id")
	@Column(name = "pid", length = 50)
	private Integer pid;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	

}

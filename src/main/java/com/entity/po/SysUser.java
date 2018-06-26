package com.entity.po;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SysUser", description = "用户对象")
@Entity
@Table(name = "sys_user")
public class SysUser implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2574621868917461750L;

	@ApiModelProperty(value = "用户id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "user_id")
	private Integer userId;
	
	@ApiModelProperty(value = "用户名称")
	@Column(name = "user_name", length = 50)
	private String  userName;
	
	@ApiModelProperty(value = "用户密码")
	@Column(name = "pass_word", length = 50)
	private String passWord;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	

}

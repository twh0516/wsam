package com.twh.wsam.data.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String loginName;
	private String password;
	/**
	 * 0管理员，1老师，2学员<br>
	 * 管理员用来创建考场（所有的工位及对应的摄像头）,修改老师登录密码
	 */
	private int userType;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
}

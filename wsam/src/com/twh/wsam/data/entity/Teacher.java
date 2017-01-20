package com.twh.wsam.data.entity;


/**
 * @author twh
 * 老师信息
 */
public class Teacher extends User {
	private static final long serialVersionUID = 1L;
	private String name;
	private String sex;
	/**
	 * 工号
	 */
	private String employeeNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}

package com.twh.wsam.data.entity;

import java.io.Serializable;

/**
 * @author twh
 *	学员
 */
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private String employeeNumber;
	private String teacherName;
	private String name;
	/**
	 * 政治面貌
	 */
	private String politicalStatus;
	/**
	 * 学号
	 */
	private String studentNo;
	private String address;
	/**
	 * 籍贯
	 */
	private String birthplace;
	/**
	 * 班级
	 */
	private String classId;
	private String phone;
	/**
	 * 专业
	 */
	private String specializedSubject;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getSpecializedSubject() {
		return specializedSubject;
	}
	public void setSpecializedSubject(String specializedSubject) {
		this.specializedSubject = specializedSubject;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}

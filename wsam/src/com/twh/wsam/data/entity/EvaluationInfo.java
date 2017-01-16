package com.twh.wsam.data.entity;

import java.io.Serializable;

/**
 * @author twh
 *	考试信息
 */
public class EvaluationInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String studentNo;
	/**
	 * 老师 工号
	 */
	private String employeeNumber;
	private String teacherName;
	private String date;
	private String startHour;
	private String startMinute;
	private String videoUrl1;
	private String videoUrl2;
	private String endHour;
	private String endMinute;
	/**
	 * 考场
	 */
	private String examinationHall;
	
	/**
	 * 考试号/工位号
	 */
	private String examinationNumber;
	/**
	 * 评语
	 */
	private String comment;
	private int level;
	private double score ;
	
	public String getVideoUrl1() {
		return videoUrl1;
	}
	public void setVideoUrl1(String videoUrl1) {
		this.videoUrl1 = videoUrl1;
	}
	public String getVideoUrl2() {
		return videoUrl2;
	}
	public void setVideoUrl2(String videoUrl2) {
		this.videoUrl2 = videoUrl2;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMinute() {
		return startMinute;
	}
	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMinute() {
		return endMinute;
	}
	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getExaminationHall() {
		return examinationHall;
	}
	public void setExaminationHall(String examinationHall) {
		this.examinationHall = examinationHall;
	}
	public String getExaminationNumber() {
		return examinationNumber;
	}
	public void setExaminationNumber(String examinationNumber) {
		this.examinationNumber = examinationNumber;
	}
	
}

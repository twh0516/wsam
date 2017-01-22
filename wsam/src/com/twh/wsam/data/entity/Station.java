package com.twh.wsam.data.entity;

import java.io.Serializable;

/**
 * @author twh
 * 工位,每个工位绑定两个摄像机
 */
public class Station implements Serializable {

	private static final long serialVersionUID = 1L;
	private String no;
	private String ExaminationNo;
	/**
	 * 
	 */
	private Camera highlightCamera;
	private Camera normalCamera;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getExaminationNo() {
		return ExaminationNo;
	}
	public void setExaminationNo(String examinationNo) {
		ExaminationNo = examinationNo;
	}
	public Camera getHighlightCamera() {
		return highlightCamera;
	}
	public void setHighlightCamera(Camera highlightCamera) {
		this.highlightCamera = highlightCamera;
	}
	public Camera getNormalCamera() {
		return normalCamera;
	}
	public void setNormalCamera(Camera normalCamera) {
		this.normalCamera = normalCamera;
	}
	
	@Override
	public String toString() {
		return no;
	}
}

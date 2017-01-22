package com.twh.wsam.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author twh
 * 硬盘录像机
 */
public class VCR implements Serializable {
	
	/**
	 * 录像机编号
	 */
	private String no;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Camera> list = new ArrayList<Camera>();
	/**
	 * 录像机ip
	 */
	private String ip;
	
	public void addCamera(Camera camera) {
		list.add(camera);
	}
	public List<Camera> getList() {
		return list;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	@Override
	public String toString() {
		return no + "_" + ip;
	}

}

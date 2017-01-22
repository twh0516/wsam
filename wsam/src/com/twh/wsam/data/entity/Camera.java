package com.twh.wsam.data.entity;

import java.io.Serializable;

/**
 * 
 * @author twh
 * 摄像头（探头）,探头都会绑定到硬盘录像机上，以保存所录视频
 *
 */
public class Camera implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 探头ip
	 */
	private String ip;
	/**
	 * 录像机ip
	 */
	private String ip_VCR;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp_VCR() {
		return ip_VCR;
	}
	public void setIp_VCR(String ip_VCR) {
		this.ip_VCR = ip_VCR;
	}
	
	@Override
	public String toString() {
		return ip;
	}
	
}

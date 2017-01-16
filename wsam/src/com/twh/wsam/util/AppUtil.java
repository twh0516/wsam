package com.twh.wsam.util;

import com.twh.util.Util;
import com.twh.util.string.StringUtil;

public class AppUtil {
	/**
	 * 获取项根目录
	 * @return
	 */
	public static String getProjectRootPath(){
		return Util.getProjectRootPath();
	}
	public static String getFFmpeg(){
		return AppUtil.getProjectRootPath() + "ffmpeg/ffmpeg";//ffmpeg工具地址
	}
	
	public static boolean isEmpty(String str) {
		return StringUtil.isEmpty(str);
	}
	
	public static String getFFmpegTime(String time) throws NumberFormatException {
		Integer t = Integer.parseInt(time);
		int hour = t / 60;
		int minute = t % 60;
		String ffTime = hour + ":" +minute + ":00";
		return ffTime;
	}
	
}

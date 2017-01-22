package com.twh.jni;


public class FileSearch {
	/**
	 * @param vSessionId
	 * @param channel
	 * @param sYear
	 * @param sMonth
	 * @param sDay
	 * @param sHour
	 * @param sMinute
	 * @param eYear
	 * @param eMonth
	 * @param eDay
	 * @param eHour
	 * @param eMinute
	 * @return 如果返回多个文件用"java"分隔
	 */
	public native static String getFiles(int vSessionId,int channel,int sYear,int sMonth,
			int sDay,int sHour,int sMinute,int eYear,int eMonth,
			int eDay,int eHour,int eMinute);
	
	
}

package com.twh.jni;

public class FileDownload {
	/**
	 * @param vSessionId
	 * @param channel
	 * @param sDate
	 * @param sMonth
	 * @param sDay
	 * @param sHour
	 * @param sMinute
	 * @param eDate
	 * @param eMonth
	 * @param eDay
	 * @param eHour
	 * @param eMinute
	 * @return -1 表示失败，其他值表示下载句柄
	 */
	public native static long downloadFileByTime(int vSessionId,int channel,
			int sYear,int sMonth,
			int sDay,int sHour,int sMinute,int eYear,int eMonth,
			int eDay,int eHour,int eMinute,String saveFile);
	
	/**
	 * @param dVRFilename
	 * @param saveFileName
	 * @return -1 表示失败，其他值表示下载句柄
	 */
	public native static long downloadFileByName(long sessionId,String dVRFilename,String saveFileName);
	/**
	 * 
	 * 
	 * @param downloadHandle下载句柄
	 * @return
	 */
	public native static int getProgressRate(long downloadHandle);
}

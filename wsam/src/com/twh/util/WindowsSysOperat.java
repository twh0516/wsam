package com.twh.util;

import java.io.IOException;

public class WindowsSysOperat {
	/**
	 * 打开windows文件或者目录<br>
	 * 如果是视频调用默认播放器播放它<br>
	 * 如果是文件调用默认程序打开它
	 * @param url
	 */
	public static void openWindowsDirectoryOrFile(String url) {
		try {
			String[] cmd = new String[5];
			cmd[0] = "cmd";
			cmd[1] = "/c";
			cmd[2] = "start";
			cmd[3] = " ";
			cmd[4] = url;
			Runtime.getRuntime().exec(cmd);
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}

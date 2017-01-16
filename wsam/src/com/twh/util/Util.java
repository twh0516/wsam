package com.twh.util;

public class Util {
	private static String projectRootPath = null;
	/**
	 * 获取项目根目录
	 * @return
	 */
	public static String getProjectRootPath(){
		if(projectRootPath == null){
			projectRootPath = System.getProperty("user.dir");//获取项目根路径
			projectRootPath = projectRootPath.replace("\\", "/");
			projectRootPath = projectRootPath + "/";
		}
		return projectRootPath;
	}
	
	/**
	 * 获取文件后缀
	 * @param fileName
	 * @return
	 */
	public static String getExtensionName(String fileName){
		if(fileName == null)
			return null;
		String[] in = fileName.split("\\.");
		if(in.length < 2){
			return null;
		}else {
			return in[1];
		}
	}
	
}

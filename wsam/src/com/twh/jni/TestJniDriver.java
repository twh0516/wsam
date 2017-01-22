package com.twh.jni;

public class TestJniDriver {
	public static void main(String args[]) {
		System.loadLibrary("wsam");
		int vSessionid = LogonDVR.logon("192.168.1.100", 8081, "admin", "123456");
		if (vSessionid != -1) {
			System.out.println("登录成功，Id:" + vSessionid);
		}else {
			System.out.println("登录失败_"+ vSessionid);
			return;
		}
//		String files = FileSearch.getFiles(vSessionid, 1, 2017, 1, 17, 0, 0, 2017, 1, 17, 23, 59);
//		System.out.println(files);
		
		long handle = FileDownload.downloadFileByName(vSessionid, "/mnt/NVR8600V_0_1/000001_0.avi", "d:/test2.avi");
		if(handle != -1 ) {
			while(true) {
				int rate = FileDownload.getProgressRate(handle) / 10;
				System.out.println("下载进度:" + rate);
				if(rate >= 100)
					break;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("退出登录 ："+LogonDVR.logonOff(vSessionid));
	}

}

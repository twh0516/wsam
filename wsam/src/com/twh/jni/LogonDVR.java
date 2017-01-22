package com.twh.jni;

public class LogonDVR {
	public native static int logon(String ip,int port,String user,String password);
	public native static int logonOff(int vSessionid);
}

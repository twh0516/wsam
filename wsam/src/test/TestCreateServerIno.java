package test;

import com.twh.util.Util;
import com.twh.wsam.data.entity.ServerInfo;
import com.twh.wsam.util.AppUtil;

public class TestCreateServerIno {
	public static void main(String args[]) {
		ServerInfo info = new ServerInfo();
		info.setDataPort(8080);
		info.setDownloadPort(8800);
		info.setIp("192.168.1.168");
		AppUtil.saveServerInfoXml(info, Util.getProjectRootPath()+"xml/", "config.xml");
	}
}

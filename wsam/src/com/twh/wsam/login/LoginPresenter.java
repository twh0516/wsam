package com.twh.wsam.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.twh.util.string.StringUtil;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.ServerInfo;
import com.twh.wsam.data.entity.Student;
import com.twh.wsam.data.entity.Teacher;
import com.twh.wsam.data.entity.User;
import com.twh.wsam.login.LoginContract.Presenter;
import com.twh.wsam.login.LoginContract.View;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.util.AppUtil;

public class LoginPresenter implements Presenter {
	private View view;
	private ServerInfo serverInfo;

	@Override
	public void start() {
		setServerInfo();
		view.showServerInfo(serverInfo.getIp(), serverInfo.getDataPort(), serverInfo.getDownloadPort());
	}

	private void setServerInfo() {
		serverInfo = new ServerInfo();
		try {
			serverInfo.setIp(AppUtil.getServerIp());
			serverInfo.setDataPort(AppUtil.getJsonPort());
			serverInfo.setDownloadPort(AppUtil.getDownloadPort());
		} catch (Exception e) {
		}
		if (StringUtil.isEmpty(serverInfo.getIp())) {
			serverInfo.setIp("");
			serverInfo.setDataPort(8080);
			serverInfo.setDownloadPort(8800);
		}
	}

	@Override
	public void logon(String ip, String textPort, String filePort, String userName, String password) {
		boolean validate = validateLogonInfo(ip, textPort, filePort, userName, password);
		if (!validate)
			return;
		// 每次登录更新一下服务器信息
		saveServerInof(ip, textPort, filePort);

		User user = new User();
		user.setCmd(CmdType.LOGON);
		user.setLoginName(userName);
		user.setPassword(StringUtil.getMD5(password.trim()));
		TextNetClient client = new TextNetClient(ip, serverInfo.getDataPort(), JSON.toJSONString(user), this);
		ThreadPool.getTextPool().submit(client);
	}

	private void saveServerInof(String ip, String textPort, String filePort) {
		serverInfo.setIp(ip);
		serverInfo.setDataPort(Integer.parseInt(textPort));
		serverInfo.setDownloadPort(Integer.parseInt(filePort));
		AppUtil.saveServerInfoXml(serverInfo, AppUtil.configPath, AppUtil.configName);
	}

	private boolean validateLogonInfo(String ip, String textPort, String filePort, String userName, String password) {
		if (StringUtil.isEmpty(ip)) {
			view.showMessage("请输入服务器IP");
			return false;
		} else if (StringUtil.isEmpty(textPort)) {
			view.showMessage("请输入文本端口");
			return false;
		} else if (StringUtil.isEmpty(filePort)) {
			view.showMessage("请输入视频端口");
			return false;
		} else if (StringUtil.isEmpty(userName)) {
			view.showMessage("请输入用户名");
			return false;
		} else if (StringUtil.isEmpty(password)) {
			view.showMessage("请输入密码");
			return false;
		}
		return true;
	}


	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}
	@Override
	public void onsubmitResult(String json) {
		CmdResult<User> cmdResult = JSON.parseObject(json, new TypeReference<CmdResult<User>>() {
		});
		int flag = cmdResult.getFlag();
		if (flag == 0) {
			view.showMessage(cmdResult.getMessage());
		} else {
			int type = cmdResult.getResultObj().getUserType();
			view.setUserType(type);
			AppUtil.cType = type;
			System.out.println(type);
			switch (type) {
			case 0:
				AppUtil.cUser = cmdResult.getResultObj();
				break;
			case 1:
				CmdResult<Teacher> teacher = JSON.parseObject(json, new TypeReference<CmdResult<Teacher>>(){});
				AppUtil.cTeacher = teacher.getResultObj();
				break;
			default:
				CmdResult<Student> student = JSON.parseObject(json, new TypeReference<CmdResult<Student>>(){});
				AppUtil.cStudent = student.getResultObj();
				break;
			}
			view.close();
		}
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}


}

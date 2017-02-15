package com.twh.wsam.setting;

import com.twh.util.string.StringUtil;
import com.twh.wsam.data.entity.VCR;
import com.twh.wsam.setting.VCRContractor.Presenter;
import com.twh.wsam.setting.VCRContractor.View;

public class VCRPresenter implements Presenter{
	private VCR vcr;
	private View view;
	@Override
	public void start() {
		if(vcr != null) {
			view.setVCR(vcr.getIp(), vcr.getPort()+"", vcr.getUser(), vcr.getPassword());
		}
	}

	@Override
	public boolean addVCR(String ip,String port,String user,String password) {
		if(!validateVCR(ip, port, user, password)) {
			return false;
		}else {
			vcr = new VCR();
			vcr.setIp(ip);
			vcr.setPort(Integer.parseInt(port));
			vcr.setUser(user);
			vcr.setPassword(password);
			return true;
		}
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	public boolean validateVCR(String ip, String port, String user, String password) {
		if (StringUtil.isEmpty(ip)) {
			view.showMessage("请输入IP");
			return false;
		} else if (StringUtil.isEmpty(port)) {
			view.showMessage("请输入端口号");
			return false;
		} else if (StringUtil.isEmpty(user)) {
			view.showMessage("请输入用户名");
			return false;
		} else if (StringUtil.isEmpty(password)) {
			view.showMessage("请输入密码");
			return false;
		}
		return true;
	}

	@Override
	public VCR getVCR() {
		return vcr;
	}
}

package com.twh.wsam.setting;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.twh.util.LoggerUtil;
import com.twh.wsam.cmd.CmdKeys;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.Camera;
import com.twh.wsam.data.entity.VCR;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.setting.AddSettingContract.Presenter;
import com.twh.wsam.setting.AddSettingContract.View;
import com.twh.wsam.util.AppUtil;

public class AddSettingPresenter implements Presenter {
	private List<VCR> list = new ArrayList<VCR>();
	private View view;

	@Override
	public boolean addVCR(VCR vcr) {
		// VCRpresenter 已检验过数据
		boolean hasTheVCR = false;
		for(VCR v : list){
			if(vcr.getIp().equals(v.getIp())) {
				hasTheVCR= true;
				break;
			}
		}
		if(!hasTheVCR){
			list.add(vcr);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean addCamera(VCR vcr, int channel) {
		Camera camera = new Camera();
		camera.setIp_VCR(vcr.getIp());
		camera.setChannel(channel);
		if (vcr.getList() == null) {
			List<Camera> lc = new ArrayList<Camera>();
			vcr.setList(lc);
		}
		List<Camera> cameraList = vcr.getList();
		boolean hasTheChannel = false;
		for(Camera c : cameraList){
			if(channel == c.getChannel()) {
				hasTheChannel = true;
				break;
			}
		}
		if(!hasTheChannel){
			vcr.getList().add(camera);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void submitSettings() {
		if (list.size() > 0) {
			TextNetClient client;
			try {
				JSONObject json = new JSONObject();
				json.put(CmdKeys.settingKey, JSON.toJSONString(list));
				if(LoggerUtil.isDebug)
					System.out.println(json);
				json.put(CmdType.CMD, CmdType.SETTINGS_SET);
				client = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(), json.toJSONString(), this);
				ThreadPool.getTextPool().submit(client);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			view.showMessage("您还未做任何配置");
		}
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void start() {
		TextNetClient client;
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(CmdType.CMD, CmdType.SETTINGS_GET);
			client = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(), jsonObject.toJSONString(), this);
			ThreadPool.getTextPool().submit(client);
			// TODO 些处应该弹出进度框阻塞
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onsubmitResult(String json) {
		CmdResult<VCR> cmdResult = JSON.parseObject(json, new TypeReference<CmdResult<VCR>>() {
		});
		if (cmdResult.getCmd() == CmdType.SETTINGS_GET) {
			// 停止阻塞对话框 TODO
			if (cmdResult.getFlag() == 1) {
				list = cmdResult.getList();
				view.showSettings(list);
			}
		} else {
			// 停止阻塞对话框TODO
			view.showMessage(cmdResult.getMessage());
		}

	}

	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}

	@Override
	public void removeVCR(String ip) {
		for (VCR vcr : list) {
			if (vcr.getIp().equals(ip)) {
				list.remove(vcr);
				break;
			}
		}
		printLog("录像机数:", list);
	}

	private void printLog(String msg, List list) {
		if (LoggerUtil.isDebug)
			System.out.println(msg + list.size());
	}

	@Override
	public void removeCamera(String ip, int channel) {
		for (VCR vcr : list) {
			if (vcr.getIp().equals(ip)) {
				for (Camera camera : vcr.getList()) {
					if (camera.getChannel() == channel) {
						vcr.getList().remove(camera);
						printLog(vcr.getIp() + "通道数", vcr.getList());
						break;
					}
				}
				break;

			}
		}
	}

}

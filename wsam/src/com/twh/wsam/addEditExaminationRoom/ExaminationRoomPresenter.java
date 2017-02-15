package com.twh.wsam.addEditExaminationRoom;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.twh.util.LoggerUtil;
import com.twh.util.string.StringUtil;
import com.twh.wsam.addEditExaminationRoom.ExaminationRoomContract.Presenter;
import com.twh.wsam.addEditExaminationRoom.ExaminationRoomContract.View;
import com.twh.wsam.cmd.CmdKeys;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.Camera;
import com.twh.wsam.data.entity.ExaminationRoom;
import com.twh.wsam.data.entity.Station;
import com.twh.wsam.data.entity.VCR;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.util.AppUtil;

public class ExaminationRoomPresenter implements Presenter {
	private List<ExaminationRoom> list = new ArrayList<ExaminationRoom>();
	private View view;
	private List<VCR> vcrList;

	@Override
	public boolean addRoom(ExaminationRoom room) {
		boolean hasTheVCR = false;
		for(ExaminationRoom r : list) {
			if(r.getRoomNo().equals(room.getRoomNo())) {
				hasTheVCR = true;
				break;
			}
		}
		if (!hasTheVCR) {
			list.add(room);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addStation(Station station, ExaminationRoom room) {
		boolean hasTheStation = false;
		if (room.getList() == null) {
			List<Station> l = new ArrayList<Station>();
			l.add(station);
			room.setList(l);
			return true;
		} else {
			for(Station s : room.getList()) {
				if(s.getNo().equals(station.getNo())){
					hasTheStation = true;
					break;
				}
			}
				
			if (!hasTheStation) {
				room.getList().add(station);
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void submitRooms() {
		if (list.size() > 0) {
			TextNetClient client;
			try {
				JSONObject json = new JSONObject();
				json.put(CmdKeys.roomKey, JSON.toJSONString(list));
				if (LoggerUtil.isDebug)
					System.out.println(json);
				json.put(CmdType.CMD, CmdType.ROOM_SET);
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
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(CmdType.CMD, CmdType.ROOM_GET);
			TextNetClient client = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(), jsonObject.toJSONString(), this);
			ThreadPool.getTextPool().submit(client);
			// TODO 些处应该弹出进度框阻塞
			
			JSONObject vcrJson = new JSONObject();
			vcrJson.put(CmdType.CMD, CmdType.SETTINGS_GET);
			TextNetClient vcrClient = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(), vcrJson.toJSONString(), this);
			ThreadPool.getTextPool().submit(vcrClient);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	synchronized public void  onsubmitResult(String json) {
		CmdResult<ExaminationRoom> cmdResult = JSON.parseObject(json, new TypeReference<CmdResult<ExaminationRoom>>() {
		});
		if (cmdResult.getCmd() == CmdType.ROOM_GET) {
			// 停止阻塞对话框 TODO
			if (cmdResult.getFlag() == 1) {
				list = cmdResult.getList();
				view.showSettings(list);
			}
		} else if(cmdResult.getCmd() == CmdType.ROOM_SET) {
			// 停止阻塞对话框TODO
			view.showMessage(cmdResult.getMessage());
		}else {
			CmdResult<VCR> vcrResult = JSON.parseObject(json, new TypeReference<CmdResult<VCR>>() {});
			vcrList = vcrResult.getList();
		}

	}

	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}

	@Override
	public void removeRoom(ExaminationRoom room) {
		list.remove(room);
		printLog("考场数:", list);
	}

	private void printLog(String msg, List list) {
		if (LoggerUtil.isDebug)
			System.out.println(msg + list.size());
	}

	@Override
	public void removeStation(ExaminationRoom room, Station station) {
		room.getList().remove(station);
		printLog(room.getRoomNo() + "工位数", room.getList());

	}

	@Override
	public boolean addCamera(Station station,boolean isFilter, Camera camera) {
		if(isFilter) {
			if(StringUtil.isEmpty(station.getFilterCameraIp())) {
				station.setFilterCameraChannel(camera.getChannel());
				station.setFilterCameraIp(camera.getIp_VCR());
				camera.setFilter("滤镜");
				return true;
			}else {
				view.showMessage("滤镜摄像头已存在！");
				return false;
			}
		}else {
			if(StringUtil.isEmpty(station.getNormalCameraIp())) {
				station.setNormalCameraChannel(camera.getChannel());
				station.setNormalCameraIp(camera.getIp_VCR());
				camera.setNormal("全景");
				return true;
			}else {
				view.showMessage("全景摄像头已存在！");
				return false;
			}
		}
	}
	
	@Override
	public VCR[] getVCRs() {
		if(vcrList == null) {
			return null;
		}else {
			VCR[] vcrs = new VCR[vcrList.size()];
			return vcrList.toArray(vcrs);
		}
	}

	@Override
	public void removeCamera(Station station, Camera camera) {
		if(!StringUtil.isEmpty(station.getFilterCameraIp())) {
			if(camera.getIp_VCR().equals(station.getFilterCameraIp())) {
				station.setFilterCameraIp("");
				station.setFilterCameraChannel(0);
			}
			
		}
		if(!StringUtil.isEmpty(station.getNormalCameraIp())) {
			if(camera.getIp_VCR().equals(station.getNormalCameraIp())) {
				station.setNormalCameraIp("");
				station.setNormalCameraChannel(0);
			}
		}
		camera.setFilter("");
		camera.setNormal("");
	}

}

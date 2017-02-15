package com.twh.wsam.examination;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.twh.util.LoggerUtil;
import com.twh.util.Util;
import com.twh.util.string.StringUtil;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.ExaminationInfo;
import com.twh.wsam.data.entity.ExaminationRoom;
import com.twh.wsam.data.entity.Station;
import com.twh.wsam.examination.AddExaminationContract.View;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.util.AppUtil;

public class AddExaminationPresenter implements AddExaminationContract.Presenter {
	private ExaminationInfo examination;
	private View view;

	public AddExaminationPresenter(ExaminationInfo examination) {
		if (examination != null)
			this.examination = examination;
		else
			this.examination = new ExaminationInfo();
	}

	@Override
	public void start() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(CmdType.CMD, CmdType.ROOM_GET);
		TextNetClient client = null;
		try {
			client = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(), jsonObject.toJSONString(), this);
			ThreadPool.getTextPool().submit(client);
		} catch (Exception e) {
			view.showMessage(Util.getExceptionStackTrace(e));
			e.printStackTrace();
		}
		if (examination.getStudentNo() != null) {
			view.setComment(examination.getComment());
			view.setEmployeeNumber(examination.getEmployeeNumber());
			view.setETime(examination.geteTime());
			view.setEvaDate(examination.getEvaDate());
			view.setFilterVideoUrl(examination.getFilterVideoUrl());
			view.setLevel(examination.getLevel() + "");
			view.setNormalVideoUrl(examination.getNormalVideoUrl());
			view.setScore(examination.getScore() + "");
			view.setSTime(examination.getsTime());
			view.setStudentName(examination.getStudentName());
			view.setStudentNo(examination.getStudentNo());
			// 考场的工位号在获取考场列表成功后再设置
		}

	}

	private ExaminationRoom getRoom(String roomNo) {
		if (roomNo == null)
			return null;
		ExaminationRoom room = null;
		if (list != null) {
			for (ExaminationRoom r : list) {
				if (r.getRoomNo().equals(roomNo)) {
					return r;
				}
			}
		}
		return room;
	}

	private Station getStation(ExaminationRoom room, String stationNo) {
		List<Station> sList = room.getList();
		if (sList != null) {
			for (Station station : room.getList()) {
				if (station.getNo().equals(stationNo)) {
					return station;
				}
			}
		}
		return null;
	}

	private List<ExaminationRoom> list;

	@Override
	public void onsubmitResult(String json) {
		JSONObject jsonObject = JSON.parseObject(json);
		int cmd = jsonObject.getIntValue(CmdType.CMD);
		if (cmd == CmdType.EXAMINATION_ADD) {
			CmdResult<ExaminationInfo> cmdResult = JSON.parseObject(json,
					new TypeReference<CmdResult<ExaminationInfo>>() {
					});
			view.showMessage(cmdResult.getMessage());
		} else {
			CmdResult<ExaminationRoom> cmdResult = JSON.parseObject(json,
					new TypeReference<CmdResult<ExaminationRoom>>() {
					});
			if (cmdResult.getFlag() == 1) {
				list = cmdResult.getList();
				ExaminationRoom[] rooms = new ExaminationRoom[list.size()];
				view.setRooms(list.toArray(rooms));

				if (examination.getStationNo() != null) {
					ExaminationRoom room = getRoom(examination.getRoomNo());
					if (room != null) {
						view.setRoomNo(room);
						setStations(room.getList());
						Station station = getStation(room, examination.getStationNo());
						if (station != null) {
							view.setStationNo(station);
						}
					}
				} else {
					setStations(list.get(0).getList());
				}
			}
		}
	}
	public void setStations(List<Station> sList) {
		if (sList != null) {
			Station[] stations = new Station[sList.size()];
			view.setStations(sList.toArray(stations));
		}
	}
	
	@Override
	public void updateStations(ItemEvent e) {
		ExaminationRoom room = (ExaminationRoom) e.getItem();
		setStations(room.getList());
	}

	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}

	@Override
	public void submitExamination() {
		boolean validate = validateExamination();
		if (!validate)
			return;
		examination.setCmd(CmdType.EXAMINATION_ADD);
		if (AppUtil.cTeacher != null)
			examination.setEmployeeNumber(AppUtil.cTeacher.getEmployeeNumber());

		TextNetClient client;
		try {
			client = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(), JSON.toJSONString(examination),
					this);
			ThreadPool.getTextPool().submit(client);
		} catch (Exception e) {
			view.showMessage(Util.getExceptionStackTrace(e));
		}
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	private boolean validateExamination() {
		if (StringUtil.isEmpty(examination.getStudentName())) {
			view.showMessage("请输入姓名");
			return false;
		} else if (StringUtil.isEmpty(examination.getStudentNo())) {
			view.showMessage("请输入学号");
			return false;
		} else if (StringUtil.isEmpty(examination.getRoomNo())) {
			view.showMessage("请输入考场号");
			return false;
		} else if (StringUtil.isEmpty(examination.getStationNo())) {
			view.showMessage("请输入工位号");
			return false;
		} else if (StringUtil.isEmpty(examination.getEvaDate())) {
			view.showMessage("请输入考试日期");
			return false;
		} else if (StringUtil.isEmpty(examination.getsTime())) {
			view.showMessage("请输入开始时间");
			return false;
		} else if (StringUtil.isEmpty(examination.geteTime())) {
			view.showMessage("请输入结束时间");
			return false;
		}
		return true;
	}

	@Override
	public void setEmployeeNumber(String teacherNo) {
		examination.setEmployeeNumber(teacherNo);
	}

	@Override
	public void setStudentNo(String studentNo) {
		examination.setStudentNo(studentNo);
	}

	@Override
	public void setStudentName(String name) {
		examination.setStudentName(name);

	}

	@Override
	public void setEvaDate(String evaDate) {
		examination.setEvaDate(evaDate);
	}

	@Override
	public void setSTime(String sTime) {
		examination.setsTime(sTime);
	}

	@Override
	public void setETime(String eTime) {
		examination.seteTime(eTime);
	}

	@Override
	public void setFilterVideoUrl(String filterVideoUrl) {
		examination.setFilterVideoUrl(filterVideoUrl);
	}

	@Override
	public void setNormalVideoUrl(String normalVideoUrl) {
		examination.setNormalVideoUrl(normalVideoUrl);
	}

	@Override
	public void setRoomNo(String roomNo) {
		examination.setRoomNo(roomNo);
	}

	@Override
	public void setStationNo(String stationNo) {
		examination.setStationNo(stationNo);
	}

	@Override
	public void setComment(String comment) {
		examination.setComment(comment);
	}

	@Override
	public void setLevel(String level) {
		if (level != null) {
			try {
				examination.setLevel(Integer.parseInt(level));
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void setScore(String score) {
		if (score != null) {
			try{
				examination.setScore(Integer.parseInt(score));
			}catch (Exception e) {
			}
		}
	}

}

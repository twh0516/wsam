package com.twh.wsam.examination;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.twh.util.Util;
import com.twh.util.string.StringUtil;
import com.twh.wsam.cmd.CmdKeys;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.ExaminationInfo;
import com.twh.wsam.examination.SearchExaminationContract.View;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.util.AppUtil;

public class SearchExaminationPresenter implements SearchExaminationContract.Presenter {
	private View view;

	@Override
	public void start() {
		
	}

	@Override
	public void onsubmitResult(String json) {
		CmdResult<ExaminationInfo> cmdResult = JSON.parseObject(json, new TypeReference<CmdResult<ExaminationInfo>>() {
		});
		if (cmdResult.getFlag() == 0) {
			view.showMessage(cmdResult.getMessage());
		} else {
			String[][] data;
			List<ExaminationInfo> list = cmdResult.getList();
			data = new String[list.size()][5];
			for (int i = 0; i < list.size(); i++) {
				data[i][0] = list.get(i).getStudentName();
				data[i][1] = list.get(i).getStudentNo();
				data[i][2] = list.get(i).getRoomNo();
				data[i][3] = list.get(i).getStationNo();
				data[i][4] = list.get(i).getEvaDate();

			}
			view.showResultTable(data);
		}
	}

	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}

	@Override
	public void searchExamination(String field,String time, int cmd) {
		boolean validate = validateField(field,time,cmd);
		if (!validate)
			return;
		JSONObject json = new JSONObject();
		json.put(CmdType.CMD, cmd);
		switch (cmd) {
		case CmdType.EXAMINATION_GET_NAME:
			json.put(CmdKeys.examinationName, field);
			break;
		case CmdType.EXAMINATION_GET_NO:
			json.put(CmdKeys.examinationNo, field);
			break;
		case CmdType.EXAMINATION_GET_TIME:
			json.put(CmdKeys.examinationDate, field);
			json.put(CmdKeys.examinationTime, time);
			break;
		}
		TextNetClient client;
		try {
			client = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(), json.toJSONString(), this);
			ThreadPool.getTextPool().submit(client);
		} catch (Exception e) {
			view.showMessage(Util.getExceptionStackTrace(e));
		}
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	private boolean validateField(String field,String time, int cmd) {
		if (StringUtil.isEmpty(field)) {
			if (cmd == CmdType.EXAMINATION_GET_NAME)
				view.showMessage("请输姓名");
			else if(cmd == CmdType.EXAMINATION_GET_NO)
				view.showMessage("请输入学号");
			else {
				view.showMessage("请输入考试日期");
			}
			return false;
		}
		if(StringUtil.isEmpty(time)) {
			if(cmd == CmdType.EXAMINATION_GET_TIME) {
				view.showMessage("请输入考试开始时间");
				return false;
			}
		}
		return true;
	}
}

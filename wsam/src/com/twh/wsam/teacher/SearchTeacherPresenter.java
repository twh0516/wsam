package com.twh.wsam.teacher;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.twh.util.Util;
import com.twh.util.string.StringUtil;
import com.twh.wsam.cmd.CmdKeys;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.Teacher;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.teacher.SearchTeacherContract.View;
import com.twh.wsam.util.AppUtil;

public class SearchTeacherPresenter implements SearchTeacherContract.Presenter {
	private View view;

	@Override
	public void start() {

	}

	@Override
	public void onsubmitResult(String json) {
		CmdResult<Teacher> cmdResult = JSON.parseObject(json, new TypeReference<CmdResult<Teacher>>() {
		});
		if(cmdResult.getFlag() == 0){ 
			view.showMessage(cmdResult.getMessage());
		}else {
			String[][] data;
			if(cmdResult.getCmd() == CmdType.TEACHER_SEARCH_NAME) {
				List<Teacher> list = cmdResult.getList();
				data =  new String[list.size()][2];
				for(int i = 0; i<list.size();i++) {
					data[i][0] = list.get(i).getName();
					data[i][1] = list.get(i).getEmployeeNumber();
//					data[i][2] = list.get(i).getPassword();
				}
			}else {
				Teacher teacher = cmdResult.getResultObj();
				data =  new String[1][2];
				data[0][0] = teacher.getName();
				data[0][1] = teacher.getEmployeeNumber();
//				data[0][2] = teacher.getPassword();
			}
			view.showResultTable(data);
		}
	}

	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}

	@Override
	public void searchTeacher(String field, int cmd) {
		boolean validate = validateField(field, cmd);
		if (!validate)
			return;
		JSONObject json = new JSONObject();
		json.put(CmdType.CMD, cmd);
		String key = cmd == CmdType.TEACHER_SEARCH_NAME ? CmdKeys.teacherName : CmdKeys.teacherNo;
		json.put(key, field);
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

	private boolean validateField(String field, int cmd) {
		if (StringUtil.isEmpty(field)) {
			if (cmd == CmdType.TEACHER_SEARCH_NAME)
				view.showMessage("请输姓名");
			else
				view.showMessage("请输入工号");
			return false;
		}
		return true;
	}
}

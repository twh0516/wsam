package com.twh.wsam.student;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.twh.util.Util;
import com.twh.util.string.StringUtil;
import com.twh.wsam.cmd.CmdKeys;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.Student;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.student.SearchStudentContract.View;
import com.twh.wsam.util.AppUtil;

public class SearchStudentPresenter implements SearchStudentContract.Presenter {
	private View view;

	@Override
	public void start() {

	}

	@Override
	public void onsubmitResult(String json) {
		CmdResult<Student> cmdResult = JSON.parseObject(json, new TypeReference<CmdResult<Student>>() {
		});
		if(cmdResult.getFlag() == 0){ 
			view.showMessage(cmdResult.getMessage());
		}else {
			String[][] data;
			if(cmdResult.getCmd() == CmdType.STUDENT_SEARCH_NAME) {
				List<Student> list = cmdResult.getList();
				data =  new String[list.size()][4];
				for(int i = 0; i<list.size();i++) {
					data[i][0] = list.get(i).getName();
					data[i][1] = list.get(i).getStudentNo();
					data[i][2] = list.get(i).getClassId();
					data[i][3] = list.get(i).getPhone();
//					data[i][3] = list.get(i).getSpecializedSubject();
					
				}
			}else {
				Student student = cmdResult.getResultObj();
				data =  new String[1][4];
				data[0][0] = student.getName();
				data[0][1] = student.getStudentNo();
				data[0][2] = student.getClassId();
				data[0][3] = student.getPhone();
//				data[0][3] = student.getSpecializedSubject();
			}
			view.showResultTable(data);
		}
	}

	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}

	@Override
	public void searchStudent(String field, int cmd) {
		boolean validate = validateField(field, cmd);
		if (!validate)
			return;
		JSONObject json = new JSONObject();
		json.put(CmdType.CMD, cmd);
		String key = cmd == CmdType.STUDENT_SEARCH_NAME ? CmdKeys.studentName: CmdKeys.studentNo;
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
			if (cmd == CmdType.STUDENT_SEARCH_NAME)
				view.showMessage("请输姓名");
			else
				view.showMessage("请输入学号");
			return false;
		}
		return true;
	}
}

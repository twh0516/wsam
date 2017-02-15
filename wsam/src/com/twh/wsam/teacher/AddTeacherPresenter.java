package com.twh.wsam.teacher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.twh.util.Util;
import com.twh.util.string.StringUtil;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.Teacher;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.teacher.AddTeacherContract.View;
import com.twh.wsam.util.AppUtil;

public class AddTeacherPresenter implements AddTeacherContract.Presenter {
	private Teacher teacher;
	private View view;

	public AddTeacherPresenter(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public void start() {
		if (teacher != null) {
			view.showTeacher(teacher.getEmployeeNumber(), teacher.getName(), teacher.getPassword());
		}

	}

	@Override
	public void onsubmitResult(String json) {
		CmdResult<Teacher> cmdResult = JSON.parseObject(json, new TypeReference<CmdResult<Teacher>>() {
		});
		view.showMessage(cmdResult.getMessage());
	}

	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}

	@Override
	public void submitTeacher(String employeeNumber, String name, String password) {
		boolean validate = validateTeacher(employeeNumber, name, password);
		if (!validate)
			return;
		if (teacher == null) {
			teacher = new Teacher();
		}
		teacher.setCmd(CmdType.TEACHER_ADD);
		teacher.setEmployeeNumber(employeeNumber);
		teacher.setLoginName(employeeNumber);
		teacher.setName(name);
		teacher.setUserType(1);
		// 需要判断用户是否修改了密码，还有md5加密码的问题 TODO
		if (StringUtil.isEmpty(teacher.getPassword())) {
			teacher.setPassword(StringUtil.getMD5(password));
		} else {
			if (!teacher.getPassword().equals(password)) {
				teacher.setPassword(StringUtil.getMD5(password));
			}
		}
		TextNetClient client;
		try {
			client = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(),
					JSON.toJSONString(teacher), this);
			ThreadPool.getTextPool().submit(client);
		} catch (Exception e) {
			view.showMessage(Util.getExceptionStackTrace(e));
		}
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	private boolean validateTeacher(String number, String name, String password) {
		if (StringUtil.isEmpty(number)) {
			view.showMessage("请输入工号");
			return false;
		} else if (StringUtil.isEmpty(name)) {
			view.showMessage("请输入姓名");
			return false;
		} else if (StringUtil.isEmpty(password)) {
			view.showMessage("请输入密码");
			return false;
		}
		return true;
	}
}

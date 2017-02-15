package com.twh.wsam.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.xml.internal.ws.util.pipe.StandaloneTubeAssembler;
import com.twh.util.Util;
import com.twh.util.string.StringUtil;
import com.twh.wsam.cmd.CmdResult;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.data.entity.Student;
import com.twh.wsam.netClient.TextNetClient;
import com.twh.wsam.netClient.ThreadPool;
import com.twh.wsam.student.AddStudentContract.View;
import com.twh.wsam.util.AppUtil;

public class AddStudentPresenter implements AddStudentContract.Presenter {
	private Student student;
	private View view;

	public AddStudentPresenter(Student student) {
		if (student != null)
			this.student = student;
		else
			this.student = new Student();
	}

	@Override
	public void start() {
		if (student.getStudentNo() != null) {
			view.setAddress(student.getAddress());
			view.setBirthplace(student.getBirthplace());
			view.setClassId(student.getClassId());
			view.setEmployeeNumber(student.getEmployeeNumber());
			view.setStudentName(student.getName());
			view.setSpecializedSubject(student.getSpecializedSubject());
			view.setPassowrd(student.getPassword());
			view.setPhone(student.getPhone());
			view.setPoliticalStatus(student.getPoliticalStatus());
			view.setStudentNo(student.getStudentNo());
		}

	}

	@Override
	public void onsubmitResult(String json) {
		CmdResult<Student> cmdResult = JSON.parseObject(json, new TypeReference<CmdResult<Student>>() {
		});
		view.showMessage(cmdResult.getMessage());
	}

	@Override
	public void sendNetMessage(String message) {
		view.showMessage(message);
	}

	@Override
	public void submitStudent() {
		boolean validate = validateTeacher(student);
		if (!validate)
			return;
		student.setCmd(CmdType.STUDENT_ADD);
		if (AppUtil.cTeacher != null)
			student.setEmployeeNumber(AppUtil.cTeacher.getEmployeeNumber());
		student.setLoginName(student.getStudentNo());// 登录名是学号
		student.setUserType(2);

		TextNetClient client;
		try {
			client = new TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(), JSON.toJSONString(student), this);
			ThreadPool.getTextPool().submit(client);
		} catch (Exception e) {
			view.showMessage(Util.getExceptionStackTrace(e));
		}
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	private boolean validateTeacher(Student student) {
		if (StringUtil.isEmpty(student.getName())) {
			view.showMessage("请输入姓名");
			return false;
		} else if (StringUtil.isEmpty(student.getStudentNo())) {
			view.showMessage("请输入学号");
			return false;
		} else if (StringUtil.isEmpty(student.getClassId())) {
			view.showMessage("请输入班级");
			return false;
		} else if (StringUtil.isEmpty(student.getPhone())) {
			view.showMessage("请输入电话");
			return false;
		}
		return true;
	}

	@Override
	public void setPassowrd(String password) {
		// 需要判断用户是否修改了密码，还有md5加密码的问题 TODO
		if (!StringUtil.isEmpty(password)) {
			if(student.getPassword() == null) {// 建档
				password = StringUtil.getMD5(password);
				student.setPassword(password);
			}else if (!student.getPassword().equals(password)) {//修改档案时如果更改了密码
				student.setPassword(StringUtil.getMD5(password));
			}
		}
	}

	@Override
	public void setEmployeeNumber(String teacherNo) {
		student.setEmployeeNumber(teacherNo);
	}

	@Override
	public void setName(String name) {
		student.setName(name);
	}

	@Override
	public void setPoliticalStatus(int politicalStatus) {
		student.setPoliticalStatus(politicalStatus);
	}

	@Override
	public void setStudentNo(String studentNo) {
		student.setStudentNo(studentNo);
	}

	@Override
	public void setAddress(String address) {
		student.setAddress(address);
	}

	@Override
	public void setBirthplace(String birthplace) {
		student.setBirthplace(birthplace);
	}

	@Override
	public void setClassId(String classId) {
		student.setClassId(classId);
	}

	@Override
	public void setPhone(String phone) {
		student.setPhone(phone);
	}

	@Override
	public void setSpecializedSubject(String specializedSubject) {
		student.setSpecializedSubject(specializedSubject);
	}
}

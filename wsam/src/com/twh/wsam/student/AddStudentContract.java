package com.twh.wsam.student;

import com.twh.wsam.ClientContract;

public interface AddStudentContract {
	interface View extends ClientContract.ClientBaseView{
		void setPresenter(Presenter presenter);
		void setEmployeeNumber(String teacherNo);
		void setStudentName(String name);
		void setPoliticalStatus(int politicalStatus);
		void setStudentNo(String studentNo);
		void setAddress(String address);
		void setBirthplace(String birthplace);
		void setClassId(String classId);
		void setPhone(String phone);
		void setPassowrd(String password);
		void setSpecializedSubject(String specializedSubject);
	}
	interface Presenter extends ClientContract.ClientBasePresenter{
		void submitStudent();
		void setView(View view);
		void setEmployeeNumber(String teacherNo);
		void setName(String name);
		void setPoliticalStatus(int politicalStatus);
		void setStudentNo(String studentNo);
		void setAddress(String address);
		void setBirthplace(String birthplace);
		void setClassId(String classId);
		void setPhone(String phone);
		void setPassowrd(String password);
		void setSpecializedSubject(String specializedSubject);
	}
}

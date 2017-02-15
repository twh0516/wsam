package com.twh.wsam.teacher;

import com.twh.wsam.ClientContract;

public interface AddTeacherContract {
	interface View extends ClientContract.ClientBaseView{
		void showTeacher(String employeeNumber,String name,String password);
		void setPresenter(Presenter presenter);
	}
	interface Presenter extends ClientContract.ClientBasePresenter{
		void submitTeacher(String employeeNumber,String name,String password);
		void setView(View view);
	}
}
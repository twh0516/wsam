package com.twh.wsam.teacher;

import com.twh.wsam.ClientContract;

public interface SearchTeacherContract {
	interface View extends ClientContract.ClientBaseView{
		void setPresenter(Presenter presenter);
		void showResultTable(String[][] data);
	}
	interface Presenter extends ClientContract.ClientBasePresenter{
		void searchTeacher(String field,int cmd);
		void setView(View view);
	}
}
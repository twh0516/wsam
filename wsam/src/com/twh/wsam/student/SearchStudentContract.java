package com.twh.wsam.student;

import com.twh.wsam.ClientContract;

public interface SearchStudentContract {
	interface View extends ClientContract.ClientBaseView{
		void setPresenter(Presenter presenter);
		void showResultTable(String[][] data);
	}
	interface Presenter extends ClientContract.ClientBasePresenter{
		void searchStudent(String field,int cmd);
		void setView(View view);
	}
}
package com.twh.wsam.examination;

import com.twh.wsam.ClientContract;

public interface SearchExaminationContract {
	interface View extends ClientContract.ClientBaseView{
		void setPresenter(Presenter presenter);
		void showResultTable(String[][] data);
	}
	interface Presenter extends ClientContract.ClientBasePresenter{
		void searchExamination(String field,String time,int cmd);
		void setView(View view);
	}
}
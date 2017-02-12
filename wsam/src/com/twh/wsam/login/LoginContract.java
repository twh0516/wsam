package com.twh.wsam.login;

import com.twh.wsam.ClientContract;

public interface LoginContract {
	interface View extends ClientContract.ClientBaseView{
		void showServerInfo(String ip,int textPort,int filePort);
		void close();
		void setUserType(int type);
	}
	
	interface Presenter extends ClientContract.ClientBasePresenter{
		void logon(String ip,String textPort,String filePort,String userName,String password);
		void setView(View view);
	}
}

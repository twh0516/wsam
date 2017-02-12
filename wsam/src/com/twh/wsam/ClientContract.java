package com.twh.wsam;


public interface ClientContract {
	interface ClientBaseView{
		void showMessage(String message);
		void start();
	}
	interface ClientBasePresenter{
		void start();
		void onsubmitResult(String json);
		void sendNetMessage(String message);
	}
}

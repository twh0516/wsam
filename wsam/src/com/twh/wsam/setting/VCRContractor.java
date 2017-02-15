package com.twh.wsam.setting;

import com.twh.wsam.data.entity.VCR;

public interface VCRContractor {
	interface View {

		void start();

		void showMessage(String message);

		void setPresenter(Presenter presenter);

		void setVCR(String ip, String port, String user, String password);
	}

	interface Presenter {
		void start();

		void setView(View view);

		boolean validateVCR(String ip, String port, String user, String password);

		boolean addVCR(String ip, String port, String user, String password);

		VCR getVCR();
	}
}

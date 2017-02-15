package com.twh.wsam.setting;
import java.util.List;

import com.twh.wsam.ClientContract.ClientBasePresenter;
import com.twh.wsam.ClientContract.ClientBaseView;
import com.twh.wsam.data.entity.VCR;;

public interface AddSettingContract {
	interface View extends ClientBaseView{
		void setPresenter(Presenter presenter);
		void setVCRPresenter(VCRContractor.Presenter presenter);
		void showSettings(List<VCR> list);
	}
	
	interface Presenter extends ClientBasePresenter{
		boolean addVCR(VCR vcr);
		boolean addCamera(VCR vcr,int channel);
		void submitSettings();
		void removeVCR(String ip);
		void removeCamera(String ip,int channel);
		void setView(View view);
	}
	
}

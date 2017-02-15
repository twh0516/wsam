package com.twh.wsam.addEditExaminationRoom;
import java.util.List;

import com.twh.wsam.ClientContract.ClientBasePresenter;
import com.twh.wsam.ClientContract.ClientBaseView;
import com.twh.wsam.data.entity.Camera;
import com.twh.wsam.data.entity.ExaminationRoom;
import com.twh.wsam.data.entity.Station;
import com.twh.wsam.data.entity.VCR;

public interface ExaminationRoomContract {
	interface View extends ClientBaseView{
		void setPresenter(Presenter presenter);
		void showSettings(List<ExaminationRoom> list);
	}
	
	interface Presenter extends ClientBasePresenter{
		boolean addRoom(ExaminationRoom room);
		boolean addStation(Station station, ExaminationRoom room);
		boolean addCamera(Station station,boolean isFilter,Camera camera);
		void submitRooms();
		void removeRoom(ExaminationRoom room);
		void removeStation(ExaminationRoom room,Station station);
		void removeCamera(Station station,Camera camera);
		void setView(View view);
		VCR[] getVCRs();
	}
	
}

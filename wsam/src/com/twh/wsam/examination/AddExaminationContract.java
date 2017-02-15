package com.twh.wsam.examination;

import java.awt.event.ItemEvent;
import java.util.List;

import com.twh.wsam.ClientContract;
import com.twh.wsam.data.entity.ExaminationRoom;
import com.twh.wsam.data.entity.Station;

public interface AddExaminationContract {
	interface View extends ClientContract.ClientBaseView{
		void setPresenter(Presenter presenter);
		void setEmployeeNumber(String teacherNo);
		void setStudentName(String name);
		void setStudentNo(String studentNo);
		void setEvaDate(String evaDate);
		void setSTime(String sTime);
		void setETime(String eTime);
		void setFilterVideoUrl(String filterVideoUrl);
		void setNormalVideoUrl(String normalVideoUrl);
		void setRoomNo(ExaminationRoom room);
		void setStationNo(Station station);
		void setComment(String comment);
		void setLevel(String level);
		void setScore(String score);
		void setRooms(ExaminationRoom[] rooms);
		void setStations(Station[] stations);
	}
	interface Presenter extends ClientContract.ClientBasePresenter{
		void submitExamination();
		void setView(View view);
		void setEmployeeNumber(String teacherNo);
		void setStudentName(String name);
		void setStudentNo(String studentNo);
		void setEvaDate(String evaDate);
		void setSTime(String sTime);
		void setETime(String eTime);
		void setFilterVideoUrl(String filterVideoUrl);
		void setNormalVideoUrl(String normalVideoUrl);
		void setRoomNo(String roomNo);
		void setStationNo(String stationNo);
		void setComment(String comment);
		void setLevel(String level);
		void setScore(String score);
		void updateStations(ItemEvent e);
	}
}

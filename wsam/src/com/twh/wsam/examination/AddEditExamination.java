package com.twh.wsam.examination;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.twh.util.calendar.CalendarChooser;
import com.twh.util.calendar.TimeChooserDialog;
import com.twh.util.string.StringUtil;
import com.twh.wsam.data.entity.ExaminationRoom;
import com.twh.wsam.data.entity.Station;
import com.twh.wsam.examination.AddExaminationContract.Presenter;
import com.twh.wsam.examination.AddExaminationContract.View;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class AddEditExamination extends JPanel implements View{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_startTime;
	private JTextField textField_endTime;
	private JTextField textDate;
	private JButton button_regetFilter;
	private JButton button_regetNormal;
	private JLabel label_1;
	private JTextPane comment;
	private JButton button_save;
	private JLabel label_2;
	private JTextField textField_score;
	private JTextField textField_level;
	private JTextField textField_name;
	private JTextField textField_studentNo;
	private Presenter presenter;
	private JButton button_getNormal = new JButton();
	private JButton button_getFilter = new JButton();
	private ExaminationRoom[] rooms;
	private Station[] stations;
	JComboBox cb_stationNo = new JComboBox();
	JComboBox cb_roomNo = new JComboBox();
	/**
	 * Create the dialog.
	 */
	public AddEditExamination() {
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBackground(Color.WHITE);
		JPanel examinationPanel = new JPanel();
		examinationPanel.setBounds(100, 100, 714, 655);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 45, 35, 35, 35, 35, 35,35,80,0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,Double.MIN_VALUE};
		int top = 10;
		examinationPanel.setLayout(gridBagLayout);
		{
			JLabel label = new JLabel("姓名：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.VERTICAL;
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 1;
			examinationPanel.add(label, gbc_label);
		}
		{
			textField_name = new JTextField();
			textField_name.setFont(new Font("Dialog", Font.PLAIN, 18));
			GridBagConstraints gbc_textField_name = new GridBagConstraints();
			gbc_textField_name.insets = new Insets(top, 0, 5, 5);
			gbc_textField_name.fill = GridBagConstraints.BOTH;
			gbc_textField_name.gridx = 1;
			gbc_textField_name.gridy = 1;
			examinationPanel.add(textField_name, gbc_textField_name);
		}
		{
			JLabel label = new JLabel("学号：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 1;
			examinationPanel.add(label, gbc_label);
		}
		{
			textField_studentNo = new JTextField();
			textField_studentNo.setFont(new Font("Dialog", Font.PLAIN, 18));
			GridBagConstraints gbc_textField_studentNo = new GridBagConstraints();
			gbc_textField_studentNo.insets = new Insets(top, 0, 5, 5);
			gbc_textField_studentNo.fill = GridBagConstraints.BOTH;
			gbc_textField_studentNo.gridx = 3;
			gbc_textField_studentNo.gridy = 1;
			examinationPanel.add(textField_studentNo, gbc_textField_studentNo);
		}
		{
			JLabel label = new JLabel("考场号：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 2;
			examinationPanel.add(label, gbc_label);
		}
		{
			
			cb_roomNo.setFont(new Font("Dialog", Font.PLAIN, 18));
			cb_roomNo.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()== ItemEvent.SELECTED) {
						presenter.updateStations(e);
					}
				}
			});
			GridBagConstraints gbc_cb_roomNo = new GridBagConstraints();
			gbc_cb_roomNo.fill = GridBagConstraints.BOTH;
			gbc_cb_roomNo.insets = new Insets(top, 0, 5, 5);
			gbc_cb_roomNo.gridx = 1;
			gbc_cb_roomNo.gridy = 2;
			examinationPanel.add(cb_roomNo, gbc_cb_roomNo);
		}
		{
			textField_level = new JTextField();
			textField_level.setHorizontalAlignment(SwingConstants.CENTER);
			textField_level.setFont(new Font("Dialog", Font.PLAIN, 18));
			textField_level.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField_level = new GridBagConstraints();
			gbc_textField_level.insets = new Insets(0, 0, 5, 5);
			gbc_textField_level.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_level.gridx = 3;
			gbc_textField_level.gridy = 4;
			examinationPanel.add(textField_level, gbc_textField_level);
		}
		{
			JLabel label = new JLabel("全景视频：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 5;
			examinationPanel.add(label, gbc_label);
		}
		{
			JLabel label = new JLabel("滤镜视频：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 5;
			examinationPanel.add(label, gbc_label);
		}
		{
			button_getNormal.setText("点击播放视频");
			button_getNormal.setHorizontalAlignment(SwingConstants.CENTER);
			button_getNormal.setFont(new Font("宋体", Font.PLAIN, 18));
			button_getNormal.setBackground(Color.WHITE);
			GridBagConstraints gbc_button_getNormal = new GridBagConstraints();
			gbc_button_getNormal.fill = GridBagConstraints.HORIZONTAL;
			gbc_button_getNormal.anchor = GridBagConstraints.NORTH;
			gbc_button_getNormal.insets = new Insets(10, 0, 5, 5);
			gbc_button_getNormal.gridx = 3;
			gbc_button_getNormal.gridy = 5;
			examinationPanel.add(button_getNormal, gbc_button_getNormal);
		}
		
		
		{
			JLabel label = new JLabel("专业等级：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 4;
			examinationPanel.add(label, gbc_label);
		}
		{
			JLabel label = new JLabel("开始时间：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 3;
			examinationPanel.add(label, gbc_label);
		}
		{
			textField_startTime = new JTextField();
			TimeChooserDialog startTimeChooser = TimeChooserDialog.getInstance("开始时间");
			startTimeChooser.register(textField_startTime);
			textField_startTime.setHorizontalAlignment(SwingConstants.CENTER);
			textField_startTime.setFont(new Font("Dialog", Font.PLAIN, 20));
			textField_startTime.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.anchor = GridBagConstraints.NORTH;
			gbc_textField.insets = new Insets(10, 0, 5, 5);
			gbc_textField.gridx = 3;
			gbc_textField.gridy = 3;
			examinationPanel.add(textField_startTime, gbc_textField);
		}
		{
			JLabel label = new JLabel("结束时间：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 4;
			examinationPanel.add(label, gbc_label);
		}
		
		{
			
			button_getFilter.setText("点击获取视频");
			button_getFilter.setHorizontalAlignment(SwingConstants.CENTER);
			button_getFilter.setFont(new Font("宋体", Font.PLAIN, 18));
			button_getFilter.setBackground(Color.WHITE);
			GridBagConstraints gbc_button_getFilter = new GridBagConstraints();
			gbc_button_getFilter.fill = GridBagConstraints.HORIZONTAL;
			gbc_button_getFilter.anchor = GridBagConstraints.NORTH;
			gbc_button_getFilter.insets = new Insets(top, 0, 5, 5);
			gbc_button_getFilter.gridx = 1;
			gbc_button_getFilter.gridy = 5;
			examinationPanel.add(button_getFilter, gbc_button_getFilter);
		}
		{
			textField_endTime = new JTextField();
			TimeChooserDialog endTimeChooser = TimeChooserDialog.getInstance("结束时间");
			endTimeChooser.register(textField_endTime);
			textField_endTime.setHorizontalAlignment(SwingConstants.CENTER);
			textField_endTime.setFont(new Font("Dialog", Font.PLAIN, 20));
			textField_endTime.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField_endTime = new GridBagConstraints();
			gbc_textField_endTime.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_endTime.anchor = GridBagConstraints.NORTH;
			gbc_textField_endTime.insets = new Insets(top, 0, 5, 5);
			gbc_textField_endTime.gridx = 1;
			gbc_textField_endTime.gridy = 4;
			examinationPanel.add(textField_endTime, gbc_textField_endTime);
		}
		{
			JLabel label = new JLabel("考试日期：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 5, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 3;
			examinationPanel.add(label, gbc_label);
		}
		
		{
			textDate = new JTextField();
			CalendarChooser calendarPanel = CalendarChooser.getInstance();
			calendarPanel.register(textDate);
			textDate.setFont(new Font("Dialog", Font.PLAIN, 20));
			GridBagConstraints gbc_textDate = new GridBagConstraints();
			gbc_textDate.fill = GridBagConstraints.BOTH;
			gbc_textDate.insets = new Insets(top, 0, 5, 5);
			gbc_textDate.gridx = 1;
			gbc_textDate.gridy = 3;
			examinationPanel.add(textDate, gbc_textDate);
		}
		{
			JLabel label = new JLabel("工位号：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 2;
			examinationPanel.add(label, gbc_label);
		}
		{
		
			cb_stationNo.setFont(new Font("Dialog", Font.PLAIN, 18));
			cb_stationNo.setBackground(Color.WHITE);
			GridBagConstraints gbc_cb_stationNo = new GridBagConstraints();
			gbc_cb_stationNo.fill = GridBagConstraints.HORIZONTAL;
			gbc_cb_stationNo.anchor = GridBagConstraints.NORTH;
			gbc_cb_stationNo.insets = new Insets(10, 0, 5, 5);
			gbc_cb_stationNo.gridx = 3;
			gbc_cb_stationNo.gridy = 2;
			examinationPanel.add(cb_stationNo, gbc_cb_stationNo);
		}
		{
			button_regetFilter = new JButton("重新获取滤镜视频");
			button_regetFilter.setFont(new Font("宋体", Font.PLAIN, 18));
			GridBagConstraints gbc_button_regetFilter = new GridBagConstraints();
			gbc_button_regetFilter.fill = GridBagConstraints.HORIZONTAL;
			gbc_button_regetFilter.anchor = GridBagConstraints.NORTH;
			gbc_button_regetFilter.insets = new Insets(top, 0, 5, 5);
			gbc_button_regetFilter.gridx = 1;
			gbc_button_regetFilter.gridy = 6;
			examinationPanel.add(button_regetFilter, gbc_button_regetFilter);
		}
		{
			button_regetNormal = new JButton("重新获取全景视频");
			button_regetNormal.setFont(new Font("宋体", Font.PLAIN, 18));
			GridBagConstraints gbc_button_regetNormal = new GridBagConstraints();
			gbc_button_regetNormal.fill = GridBagConstraints.HORIZONTAL;
			gbc_button_regetNormal.anchor = GridBagConstraints.NORTH;
			gbc_button_regetNormal.insets = new Insets(10, 0, 5, 5);
			gbc_button_regetNormal.gridx = 3;
			gbc_button_regetNormal.gridy = 6;
			examinationPanel.add(button_regetNormal, gbc_button_regetNormal);
		}
		{
			label_2 = new JLabel("评分：");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label_2 = new GridBagConstraints();
			gbc_label_2.anchor = GridBagConstraints.EAST;
			gbc_label_2.fill = GridBagConstraints.VERTICAL;
			gbc_label_2.insets = new Insets(top, 0, 5, 5);
			gbc_label_2.gridx = 0;
			gbc_label_2.gridy = 7;
			examinationPanel.add(label_2, gbc_label_2);
		}
		{
			textField_score = new JTextField();
			textField_score.setHorizontalAlignment(SwingConstants.CENTER);
			textField_score.setFont(new Font("Dialog", Font.PLAIN, 18));
			textField_score.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField_score = new GridBagConstraints();
			gbc_textField_score.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_score.insets = new Insets(top, 0, 5, 5);
			gbc_textField_score.gridx = 1;
			gbc_textField_score.gridy = 7;
			examinationPanel.add(textField_score, gbc_textField_score);
		}
		{
			label_1 = new JLabel("评语：");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label_1 = new GridBagConstraints();
			gbc_label_1.anchor = GridBagConstraints.NORTHEAST;
			gbc_label_1.insets = new Insets(top, 0, 5, 5);
			gbc_label_1.gridx = 0;
			gbc_label_1.gridy = 8;
			examinationPanel.add(label_1, gbc_label_1);
		}
		
		comment = new JTextPane();
		comment.setFont(new Font("Dialog", Font.PLAIN, 18));
		JScrollPane scrollPane = new JScrollPane(comment);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(10, 0, 5, 5);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 8;
		examinationPanel.add(scrollPane, gbc_scrollPane);
		{
			button_save = new JButton("保存");
			button_save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					presenter.setStudentName(textField_name.getText());
					presenter.setStudentNo(textField_studentNo.getText());
					presenter.setEvaDate(textDate.getText());
					presenter.setSTime(textField_startTime.getText());
					presenter.setETime(textField_endTime.getText());
					presenter.setRoomNo(((ExaminationRoom)cb_roomNo.getSelectedItem()).getRoomNo());
					presenter.setStationNo(((Station)cb_stationNo.getSelectedItem()).getNo());
					presenter.setComment(comment.getText());
					presenter.setLevel(textField_level.getText());
					presenter.setScore(textField_score.getText());
					
					presenter.submitExamination();
				}
			});
			button_save.setFont(new Font("宋体", Font.PLAIN, 23));
			GridBagConstraints gbc_button_save = new GridBagConstraints();
			gbc_button_save.insets = new Insets(0, 0, 0, 5);
			gbc_button_save.gridwidth = 4;
			gbc_button_save.gridx = 0;
			gbc_button_save.gridy = 9;
			examinationPanel.add(button_save, gbc_button_save);
		}
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(examinationPanel, GroupLayout.PREFERRED_SIZE, 733, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(158, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(examinationPanel, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		{
		}
	}

	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void start() {
		presenter.start();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setEmployeeNumber(String teacherNo) {
		
	}

	@Override
	public void setStudentName(String name) {
		textField_name.setText(name);
	}

	@Override
	public void setStudentNo(String studentNo) {
		textField_studentNo.setText(studentNo);
	}

	@Override
	public void setEvaDate(String evaDate) {
		textDate.setText(evaDate);
	}

	@Override
	public void setSTime(String sTime) {
		textField_startTime.setText(sTime);
	}

	@Override
	public void setETime(String eTime) {
		textField_endTime.setText(eTime);
	}
	public static String playFilterVideo = "播放滤镜视频";
	public static String getFilterVideo = "获取滤镜视频";
	public static String playNormalVideo = "播放全景视频";
	public static String getNormalVideo = "获取全景视频";
	
	@Override
	public void setFilterVideoUrl(String filterVideoUrl) {
		if(!StringUtil.isEmpty(filterVideoUrl)){
			button_getFilter.setText(playFilterVideo);
		}else {
			button_getFilter.setText(getFilterVideo);
		}
	}

	@Override
	public void setNormalVideoUrl(String normalVideoUrl) {
		if(!StringUtil.isEmpty(normalVideoUrl)){
			button_getNormal.setText(playNormalVideo);
		}else {
			button_getNormal.setText(getNormalVideo);
		}
	}

	@Override
	public void setRoomNo(ExaminationRoom room) {
		if(rooms != null)
			cb_roomNo.setSelectedItem(room);
	}

	@Override
	public void setStationNo(Station station) {
		if(stations != null) {
			cb_stationNo.setSelectedItem(station);
		}
	}

	@Override
	public void setComment(String comment) {
		this.comment.setText(comment);
	}

	@Override
	public void setLevel(String level) {
		textField_level.setText(level);
	}

	@Override
	public void setScore(String score) {
		textField_score.setText(score);
	}

	@Override
	public void setRooms(ExaminationRoom[] rooms) {
		this.rooms = rooms;
		cb_roomNo.setModel(new DefaultComboBoxModel<>(rooms));
	}
	@Override
	public void setStations(Station[] stations) {
		this.stations = stations;
		cb_stationNo.setModel(new DefaultComboBoxModel<>(stations));
	}

}

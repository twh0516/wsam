package com.twh.wsam.examination;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.mkk.swing.JTimeChooser;
import com.twh.util.calendar.CalendarChooser;
import com.twh.util.calendar.TimeChooser;
import com.twh.util.calendar.TimeChooserDialog;
import com.twh.wsam.Numbering;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEditExamination extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_startTime;
	private JTextField textField_endTime;
	private JTextField textDate;
	private JButton button_1;
	private JButton button_2;
	private JLabel label_1;
	private JTextPane comment;
	private JButton button_3;
	private JLabel label_2;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;


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
			textField_5 = new JTextField();
			textField_5.setFont(new Font("Dialog", Font.PLAIN, 18));
			GridBagConstraints gbc_textField_5 = new GridBagConstraints();
			gbc_textField_5.insets = new Insets(top, 0, 5, 5);
			gbc_textField_5.fill = GridBagConstraints.BOTH;
			gbc_textField_5.gridx = 1;
			gbc_textField_5.gridy = 1;
			examinationPanel.add(textField_5, gbc_textField_5);
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
			textField_6 = new JTextField();
			textField_6.setFont(new Font("Dialog", Font.PLAIN, 18));
			GridBagConstraints gbc_textField_6 = new GridBagConstraints();
			gbc_textField_6.insets = new Insets(top, 0, 5, 5);
			gbc_textField_6.fill = GridBagConstraints.BOTH;
			gbc_textField_6.gridx = 3;
			gbc_textField_6.gridy = 1;
			examinationPanel.add(textField_6, gbc_textField_6);
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
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Dialog", Font.PLAIN, 18));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(top, 0, 5, 5);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 2;
			examinationPanel.add(comboBox, gbc_comboBox);
		}
		{
			textField_4 = new JTextField();
			textField_4.setHorizontalAlignment(SwingConstants.CENTER);
			textField_4.setFont(new Font("Dialog", Font.PLAIN, 18));
			textField_4.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 5);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 3;
			gbc_textField_4.gridy = 4;
			examinationPanel.add(textField_4, gbc_textField_4);
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
			JLabel label = new JLabel("普通视频：");
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
			JButton button = new JButton();
			button.setText("点击播放视频");
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setFont(new Font("宋体", Font.PLAIN, 18));
			button.setBackground(Color.WHITE);
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.fill = GridBagConstraints.HORIZONTAL;
			gbc_button.anchor = GridBagConstraints.NORTH;
			gbc_button.insets = new Insets(10, 0, 5, 5);
			gbc_button.gridx = 3;
			gbc_button.gridy = 5;
			examinationPanel.add(button, gbc_button);
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
			JButton button = new JButton();
			button.setText("点击获取视频");
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setFont(new Font("宋体", Font.PLAIN, 18));
			button.setBackground(Color.WHITE);
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.fill = GridBagConstraints.HORIZONTAL;
			gbc_button.anchor = GridBagConstraints.NORTH;
			gbc_button.insets = new Insets(top, 0, 5, 5);
			gbc_button.gridx = 1;
			gbc_button.gridy = 5;
			examinationPanel.add(button, gbc_button);
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
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Dialog", Font.PLAIN, 18));
			comboBox.setBackground(Color.WHITE);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.anchor = GridBagConstraints.NORTH;
			gbc_comboBox.insets = new Insets(10, 0, 5, 5);
			gbc_comboBox.gridx = 3;
			gbc_comboBox.gridy = 2;
			examinationPanel.add(comboBox, gbc_comboBox);
		}
		{
			button_1 = new JButton("重新获取全景视频");
			button_1.setFont(new Font("宋体", Font.PLAIN, 18));
			GridBagConstraints gbc_button_1 = new GridBagConstraints();
			gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_button_1.anchor = GridBagConstraints.NORTH;
			gbc_button_1.insets = new Insets(top, 0, 5, 5);
			gbc_button_1.gridx = 1;
			gbc_button_1.gridy = 6;
			examinationPanel.add(button_1, gbc_button_1);
		}
		{
			button_2 = new JButton("重新获取普通视频");
			button_2.setFont(new Font("宋体", Font.PLAIN, 18));
			GridBagConstraints gbc_button_2 = new GridBagConstraints();
			gbc_button_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_button_2.anchor = GridBagConstraints.NORTH;
			gbc_button_2.insets = new Insets(10, 0, 5, 5);
			gbc_button_2.gridx = 3;
			gbc_button_2.gridy = 6;
			examinationPanel.add(button_2, gbc_button_2);
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
			textField_2 = new JTextField();
			textField_2.setHorizontalAlignment(SwingConstants.CENTER);
			textField_2.setFont(new Font("Dialog", Font.PLAIN, 18));
			textField_2.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.insets = new Insets(top, 0, 5, 5);
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 7;
			examinationPanel.add(textField_2, gbc_textField_2);
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
			button_3 = new JButton("保存");
			button_3.setFont(new Font("宋体", Font.PLAIN, 23));
			GridBagConstraints gbc_button_3 = new GridBagConstraints();
			gbc_button_3.insets = new Insets(0, 0, 0, 5);
			gbc_button_3.gridwidth = 4;
			gbc_button_3.gridx = 0;
			gbc_button_3.gridy = 9;
			examinationPanel.add(button_3, gbc_button_3);
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

}

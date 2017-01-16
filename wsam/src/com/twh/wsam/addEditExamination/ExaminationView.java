package com.twh.wsam.addEditExamination;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class ExaminationView extends JPanel {
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton textField_5;
	private JButton textField_6;

	/**
	 * Create the panel.
	 */
	public ExaminationView() {
		setBackground(new Color(255, 255, 255));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel label = new JLabel("田文海的测评",SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, label, 58, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 454, SpringLayout.WEST, this);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 25));
		label.setBackground(SystemColor.activeCaption);
		add(label);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 61, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.WEST, panel, 113, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 268, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.EAST, panel, -145, SpringLayout.EAST, this);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 20));
		
		JLabel label_9 = new JLabel("考场号：");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_9);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBackground(Color.WHITE);
		panel.add(comboBox);
		
		JLabel label_10 = new JLabel("工位号：");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_10);
		
		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1);
		
		JLabel label_11 = new JLabel("考试日期：");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_11);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setBackground(Color.WHITE);
		panel.add(textField_2);
		
		JLabel label_12 = new JLabel("开始时间：");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_12);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_3.setBackground(Color.WHITE);
		panel.add(textField_3);
		
		JLabel label_13 = new JLabel("结束时间：");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_13);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_4.setBackground(Color.WHITE);
		panel.add(textField_4);
		
		JLabel label_8 = new JLabel("专业等级：");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_8);
		
		JTextField comboBox_2 = new JTextField();
		panel.add(comboBox_2);
		
		JLabel label_7 = new JLabel("普通视频：");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_7);
		
		textField_5 = new JButton();
		textField_5.setText("点击获取视频");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_5.setBackground(Color.WHITE);
		panel.add(textField_5);
		
		JLabel label_6 = new JLabel("全景视频：");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_6);
		
		textField_6 = new JButton();
		textField_6.setText("点击播放视频");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_6.setBackground(Color.WHITE);
		panel.add(textField_6);
		
		JButton button = new JButton("重新获取全景视频");
		springLayout.putConstraint(SpringLayout.NORTH, button, 44, SpringLayout.SOUTH, panel);
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		add(button);
		
		JButton button_1 = new JButton("重新获取普通视频");
		springLayout.putConstraint(SpringLayout.EAST, button, -209, SpringLayout.WEST, button_1);
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.EAST, button_1, -157, SpringLayout.EAST, this);
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		add(button_1);
		
		JLabel label_1 = new JLabel("评语：");
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 87, SpringLayout.SOUTH, button);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 222, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, label_1, 325, SpringLayout.WEST, this);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		add(label_1);
		
		JTextPane textPane = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 69, SpringLayout.SOUTH, button);
		springLayout.putConstraint(SpringLayout.WEST, textPane, 0, SpringLayout.EAST, label_1);
		springLayout.putConstraint(SpringLayout.SOUTH, textPane, 136, SpringLayout.SOUTH, button);
		springLayout.putConstraint(SpringLayout.EAST, textPane, 298, SpringLayout.EAST, label);
		textPane.setBackground(SystemColor.controlHighlight);
		add(textPane);
		
		JButton button_2 = new JButton("保存");
		button_2.setFont(new Font("宋体", Font.PLAIN, 25));
		springLayout.putConstraint(SpringLayout.NORTH, button_2, 50, SpringLayout.SOUTH, textPane);
		springLayout.putConstraint(SpringLayout.EAST, button_2, -477, SpringLayout.EAST, this);
		add(button_2);

	}

}

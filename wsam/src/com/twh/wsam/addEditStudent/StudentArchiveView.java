package com.twh.wsam.addEditStudent;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JButton;

public class StudentArchiveView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Create the panel.
	 */
	public StudentArchiveView() {
		setBackground(Color.WHITE);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 171, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, 897, SpringLayout.WEST, this);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 20));
		
		JLabel label = new JLabel("姓名：");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBackground(Color.WHITE);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("学号：");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBackground(Color.WHITE);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("班级：");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setBackground(Color.WHITE);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("专业：");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_3.setBackground(Color.WHITE);
		panel.add(textField_3);
		
		JLabel label_4 = new JLabel("联系电话：");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_4.setBackground(Color.WHITE);
		panel.add(textField_4);
		
		JLabel label_5 = new JLabel("籍贯：");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_5.setBackground(Color.WHITE);
		panel.add(textField_5);
		
		JLabel label_6 = new JLabel("家庭住址：");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_6.setBackground(Color.WHITE);
		panel.add(textField_6);
		
		JLabel label_7 = new JLabel("政治面貌：");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBackground(Color.WHITE);
		panel.add(comboBox);
		
		JLabel label_8 = new JLabel("考场号：");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_8);
		
		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1);
		
		JLabel label_9 = new JLabel("工位号：");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_9);
		
		JComboBox comboBox_2 = new JComboBox();
		panel.add(comboBox_2);
		
		JLabel label_10 = new JLabel("考试日期：");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_10);
		
		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_7.setBackground(Color.WHITE);
		panel.add(textField_7);
		
		JLabel label_11 = new JLabel("开始时间：");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_11);
		
		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_8.setBackground(Color.WHITE);
		panel.add(textField_8);
		
		JLabel label_12 = new JLabel("结束时间：");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_12);
		
		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_9.setBackground(Color.WHITE);
		panel.add(textField_9);
		
		JLabel label_13 = new JLabel("学员建档");
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 360, SpringLayout.SOUTH, label_13);
		springLayout.putConstraint(SpringLayout.SOUTH, label_13, -539, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 30, SpringLayout.SOUTH, label_13);
		springLayout.putConstraint(SpringLayout.WEST, label_13, 475, SpringLayout.WEST, this);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("宋体", Font.BOLD, 35));
		label_13.setBackground(SystemColor.activeCaption);
		add(label_13);
		
		JButton button = new JButton("保存");
		springLayout.putConstraint(SpringLayout.NORTH, button, 64, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, button, 502, SpringLayout.WEST, this);
		button.setFont(new Font("宋体", Font.PLAIN, 25));
		add(button);

	}

}

package com.twh.wsam.addEditTeacher;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.twh.wsam.Numbering;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class TeacherView extends JPanel implements Numbering {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField textField_3;

	/**
	 * Create the panel.
	 */
	public TeacherView() {
		setBackground(Color.WHITE);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JButton button = new JButton("保存");
		springLayout.putConstraint(SpringLayout.WEST, button, 242, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 330, SpringLayout.NORTH, this);
		button.setBackground(Color.WHITE);
		button.setFont(new Font("宋体", Font.PLAIN, 25));
		add(button);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, 600, SpringLayout.WEST, this);
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
		
		JLabel label_1 = new JLabel("工号:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBackground(Color.WHITE);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("用户名");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setBackground(Color.WHITE);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("密码:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_3);
		
		textField_3 = new JPasswordField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_3.setBackground(Color.WHITE);
		panel.add(textField_3);
		
		JLabel label_4 = new JLabel("教师信息");
		springLayout.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, label_4);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 65, SpringLayout.SOUTH, label_4);
		springLayout.putConstraint(SpringLayout.NORTH, label_4, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, label_4, 242, SpringLayout.WEST, this);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.BOLD, 25));
		label_4.setBackground(SystemColor.activeCaption);
		add(label_4);

	}

	@Override
	public int getNo() {
		return 2;
	}
}

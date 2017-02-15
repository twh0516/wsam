package com.twh.wsam.teacher;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.twh.wsam.teacher.AddTeacherContract.Presenter;
import com.twh.wsam.teacher.AddTeacherContract.View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class AddEditTeacher extends JPanel implements View{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jTextName;
	private JTextField jTextNumber;
	private JPasswordField jTextPassword;
	private Presenter presenter;

	/**
	 * Create the panel.
	 */
	public AddEditTeacher() {
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setSize(new Dimension(400, 500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0,40};
		gridBagLayout.rowHeights = new int[] {80, 60, 60, 100, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0,0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("姓名：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(40, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		jTextName = new JTextField();
		jTextName.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_jTextName = new GridBagConstraints();
		gbc_jTextName.fill = GridBagConstraints.BOTH;
		gbc_jTextName.insets = new Insets(40, 0, 5, 0);
		gbc_jTextName.gridx = 1;
		gbc_jTextName.gridy = 0;
		panel.add(jTextName, gbc_jTextName);
		jTextName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("工号：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(20, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jTextNumber = new JTextField();
		jTextNumber.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_jTextNumber = new GridBagConstraints();
		gbc_jTextNumber.fill = GridBagConstraints.BOTH;
		gbc_jTextNumber.insets = new Insets(20, 0, 5, 0);
		gbc_jTextNumber.gridx = 1;
		gbc_jTextNumber.gridy = 1;
		panel.add(jTextNumber, gbc_jTextNumber);
		jTextNumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("登录密码：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(20, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jTextPassword = new JPasswordField();
		jTextPassword.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_jTextPassword = new GridBagConstraints();
		gbc_jTextPassword.fill = GridBagConstraints.BOTH;
		gbc_jTextPassword.insets = new Insets(20, 0, 5, 0);
		gbc_jTextPassword.gridx = 1;
		gbc_jTextPassword.gridy = 2;
		panel.add(jTextPassword, gbc_jTextPassword);
		jTextPassword.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(87, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(181, Short.MAX_VALUE))
		);
		
		JButton submit = new JButton("提交");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.submitTeacher(jTextNumber.getText(), jTextName.getText(), jTextPassword.getText());
			}
		});
		submit.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_submit = new GridBagConstraints();
		gbc_submit.insets = new Insets(0, 0, 5, 0);
		gbc_submit.gridwidth = 3;
		gbc_submit.gridx = 0;
		gbc_submit.gridy = 3;
		panel.add(submit, gbc_submit);
		setLayout(groupLayout);

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
	public void showTeacher(String employeeNumber, String name, String password) {
		jTextName.setText(name);
		jTextNumber.setText(employeeNumber);
		jTextPassword.setText(password);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}

package com.twh.wsam.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.twh.wsam.student.AddStudentContract.Presenter;
import com.twh.wsam.student.AddStudentContract.View;

public class StudentArchive extends JPanel implements View{
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField studentNo;
	private JTextField classId;
	private JTextField specializedSubject;
	private JTextField phone;
	private JTextField birthplace;
	private JTextField address;
	private JPasswordField text_password;
	private Presenter presenter;
	private JComboBox jCpoliticalStatus = new JComboBox();

	/**
	 * Create the panel.
	 */
	public StudentArchive(Frame parent) {
		setBackground(Color.WHITE);
		JPanel studentPanel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 110,200, 100, 200, 0,0};
		gbl_panel.rowHeights = new int[] { 60, 60, 60, 60, 60, 60, 80, 0 };
		gbl_panel.columnWeights = new double[] { 0.0,1.0, 0.0, 0.0, 0.0,Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 , Double.MIN_VALUE};
		studentPanel.setLayout(gbl_panel);
		
		int top = 20;
		JLabel label = new JLabel("姓名：");
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(top, 20, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		studentPanel.add(label, gbc_label);
		
				nameField = new JTextField();
				nameField.setHorizontalAlignment(SwingConstants.CENTER);
				nameField.setFont(new Font("宋体", Font.PLAIN, 20));
				nameField.setBackground(Color.WHITE);
				GridBagConstraints gbc_nameField = new GridBagConstraints();
				gbc_nameField.fill = GridBagConstraints.BOTH;
				gbc_nameField.insets = new Insets(top, 0, 5, 5);
				gbc_nameField.gridx = 1;
				gbc_nameField.gridy = 0;
				studentPanel.add(nameField, gbc_nameField);
		
				JLabel label_1 = new JLabel("学号：");
				label_1.setHorizontalAlignment(SwingConstants.RIGHT);
				label_1.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_1 = new GridBagConstraints();
				gbc_label_1.fill = GridBagConstraints.BOTH;
				gbc_label_1.insets = new Insets(top, 0, 5, 5);
				gbc_label_1.gridx = 2;
				gbc_label_1.gridy = 0;
				studentPanel.add(label_1, gbc_label_1);
		
				studentNo = new JTextField();
				studentNo.setHorizontalAlignment(SwingConstants.CENTER);
				studentNo.setFont(new Font("宋体", Font.PLAIN, 20));
				studentNo.setBackground(Color.WHITE);
				GridBagConstraints gbc_studentNo = new GridBagConstraints();
				gbc_studentNo.fill = GridBagConstraints.BOTH;
				gbc_studentNo.insets = new Insets(top, 0, 5, 5);
				gbc_studentNo.gridx = 3;
				gbc_studentNo.gridy = 0;
				studentPanel.add(studentNo, gbc_studentNo);
		
				JLabel label_2 = new JLabel("班级：");
				label_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
				label_2.setHorizontalAlignment(SwingConstants.RIGHT);
				label_2.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_2 = new GridBagConstraints();
				gbc_label_2.fill = GridBagConstraints.BOTH;
				gbc_label_2.insets = new Insets(top, 0, 5, 5);
				gbc_label_2.gridx = 0;
				gbc_label_2.gridy = 1;
				studentPanel.add(label_2, gbc_label_2);
		
				classId = new JTextField();
				classId.setHorizontalAlignment(SwingConstants.CENTER);
				classId.setFont(new Font("宋体", Font.PLAIN, 20));
				classId.setBackground(Color.WHITE);
				GridBagConstraints gbc_classId = new GridBagConstraints();
				gbc_classId.fill = GridBagConstraints.BOTH;
				gbc_classId.insets = new Insets(top, 0, 5, 5);
				gbc_classId.gridx = 1;
				gbc_classId.gridy = 1;
				studentPanel.add(classId, gbc_classId);
		
				JLabel label_3 = new JLabel("专业：");
				label_3.setHorizontalAlignment(SwingConstants.RIGHT);
				label_3.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_3 = new GridBagConstraints();
				gbc_label_3.fill = GridBagConstraints.BOTH;
				gbc_label_3.insets = new Insets(top, 0, 5, 5);
				gbc_label_3.gridx = 2;
				gbc_label_3.gridy = 1;
				studentPanel.add(label_3, gbc_label_3);
		
				specializedSubject = new JTextField();
				specializedSubject.setHorizontalAlignment(SwingConstants.CENTER);
				specializedSubject.setFont(new Font("宋体", Font.PLAIN, 20));
				specializedSubject.setBackground(Color.WHITE);
				GridBagConstraints gbc_specializedSubject = new GridBagConstraints();
				gbc_specializedSubject.fill = GridBagConstraints.BOTH;
				gbc_specializedSubject.insets = new Insets(top, 0, 5, 5);
				gbc_specializedSubject.gridx = 3;
				gbc_specializedSubject.gridy = 1;
				studentPanel.add(specializedSubject, gbc_specializedSubject);
		
				JLabel label_4 = new JLabel("联系电话：");
				label_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
				label_4.setHorizontalAlignment(SwingConstants.RIGHT);
				label_4.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_4 = new GridBagConstraints();
				gbc_label_4.fill = GridBagConstraints.BOTH;
				gbc_label_4.insets = new Insets(top, 20, 5, 5);
				gbc_label_4.gridx = 0;
				gbc_label_4.gridy = 2;
				studentPanel.add(label_4, gbc_label_4);
				
						phone = new JTextField();
						phone.setHorizontalAlignment(SwingConstants.CENTER);
						phone.setFont(new Font("宋体", Font.PLAIN, 20));
						phone.setBackground(Color.WHITE);
						GridBagConstraints gbc_phone = new GridBagConstraints();
						gbc_phone.fill = GridBagConstraints.BOTH;
						gbc_phone.insets = new Insets(top, 0, 5, 5);
						gbc_phone.gridx = 1;
						gbc_phone.gridy = 2;
						studentPanel.add(phone, gbc_phone);
		
				JLabel label_7 = new JLabel("政治面貌：");
				label_7.setHorizontalAlignment(SwingConstants.RIGHT);
				label_7.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_7 = new GridBagConstraints();
				gbc_label_7.fill = GridBagConstraints.BOTH;
				gbc_label_7.insets = new Insets(top, 0, 5, 5);
				gbc_label_7.gridx = 2;
				gbc_label_7.gridy = 2;
				studentPanel.add(label_7, gbc_label_7);
		
				
				jCpoliticalStatus.setModel(new DefaultComboBoxModel<>(politicalStatuss));
				jCpoliticalStatus.setFont(new Font("宋体", Font.PLAIN, 20));
				jCpoliticalStatus.setBackground(Color.WHITE);
				GridBagConstraints gbc_politicalStatus = new GridBagConstraints();
				gbc_politicalStatus.insets = new Insets(top, 0, 5, 5);
				gbc_politicalStatus.fill = GridBagConstraints.BOTH;
				gbc_politicalStatus.gridx = 3;
				gbc_politicalStatus.gridy = 2;
				studentPanel.add(jCpoliticalStatus, gbc_politicalStatus);
				
				JLabel label_8 = new JLabel("登录密码：");
				label_8.setHorizontalAlignment(SwingConstants.RIGHT);
				label_8.setFont(new Font("宋体", Font.PLAIN, 20));
				label_8.setAlignmentX(1.0f);
				GridBagConstraints gbc_label_8 = new GridBagConstraints();
				gbc_label_8.fill = GridBagConstraints.VERTICAL;
				gbc_label_8.anchor = GridBagConstraints.EAST;
				gbc_label_8.insets = new Insets(20, 0, 5, 5);
				gbc_label_8.gridx = 0;
				gbc_label_8.gridy = 3;
				studentPanel.add(label_8, gbc_label_8);
				
				text_password = new JPasswordField();
				text_password.setHorizontalAlignment(SwingConstants.CENTER);
				text_password.setFont(new Font("宋体", Font.PLAIN, 20));
				text_password.setBackground(Color.WHITE);
				GridBagConstraints gbc_text_password = new GridBagConstraints();
				gbc_text_password.gridwidth = 3;
				gbc_text_password.insets = new Insets(20, 0, 5, 5);
				gbc_text_password.fill = GridBagConstraints.BOTH;
				gbc_text_password.gridx = 1;
				gbc_text_password.gridy = 3;
				studentPanel.add(text_password, gbc_text_password);
		
				JLabel label_6 = new JLabel("家庭住址：");
				label_6.setAlignmentX(Component.RIGHT_ALIGNMENT);
				label_6.setHorizontalAlignment(SwingConstants.RIGHT);
				label_6.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_6 = new GridBagConstraints();
				gbc_label_6.fill = GridBagConstraints.BOTH;
				gbc_label_6.insets = new Insets(top, 0, 5, 5);
				gbc_label_6.gridx = 0;
				gbc_label_6.gridy = 4;
				studentPanel.add(label_6, gbc_label_6);
		
				address = new JTextField();
				address.setHorizontalAlignment(SwingConstants.CENTER);
				address.setFont(new Font("宋体", Font.PLAIN, 20));
				address.setBackground(Color.WHITE);
				GridBagConstraints gbc_address = new GridBagConstraints();
				gbc_address.gridwidth = 3;
				gbc_address.insets = new Insets(top, 0, 5, 5);
				gbc_address.fill = GridBagConstraints.BOTH;
				gbc_address.gridx = 1;
				gbc_address.gridy = 4;
				studentPanel.add(address, gbc_address);
		
				JLabel label_5 = new JLabel("籍贯：");
				label_5.setAlignmentX(Component.RIGHT_ALIGNMENT);
				label_5.setHorizontalAlignment(SwingConstants.RIGHT);
				label_5.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_5 = new GridBagConstraints();
				gbc_label_5.fill = GridBagConstraints.BOTH;
				gbc_label_5.insets = new Insets(top, 0, 5, 5);
				gbc_label_5.gridx = 0;
				gbc_label_5.gridy = 5;
				studentPanel.add(label_5, gbc_label_5);

		JButton button = new JButton("保存档案");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.setAddress(address.getText());
				presenter.setBirthplace(birthplace.getText());
				presenter.setClassId(classId.getText());
				presenter.setName(nameField.getText());
				presenter.setPassowrd(String.valueOf(text_password.getPassword()));
				presenter.setSpecializedSubject(specializedSubject.getText());
				presenter.setPhone(phone.getText());
				presenter.setPoliticalStatus(jCpoliticalStatus.getSelectedIndex());
				presenter.setStudentNo(studentNo.getText());
				presenter.submitStudent();
			}
		});
		
				birthplace = new JTextField();
				birthplace.setHorizontalAlignment(SwingConstants.CENTER);
				birthplace.setFont(new Font("宋体", Font.PLAIN, 20));
				birthplace.setBackground(Color.WHITE);
				GridBagConstraints gbc_birthplace = new GridBagConstraints();
				gbc_birthplace.insets = new Insets(top, 0, 5, 5);
				gbc_birthplace.fill = GridBagConstraints.BOTH;
				gbc_birthplace.gridwidth = 3;
				gbc_birthplace.gridx = 1;
				gbc_birthplace.gridy = 5;
				studentPanel.add(birthplace, gbc_birthplace);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 4;
		gbc_button.insets = new Insets(0, 0, 0, 40);
		gbc_button.gridx = 0;
		gbc_button.gridy = 6;
		studentPanel.add(button, gbc_button);
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(studentPanel, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(studentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	private static void createRoom() {
		JFrame frame = new JFrame("学生档案测试");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StudentArchive room = new StudentArchive(frame);
		frame.getContentPane().add(room, BorderLayout.CENTER);
		frame.setSize(800, 500);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createRoom();
			}
		});
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
	
	String politicalStatuss[] = {"群众","党员","团员"};

	@Override
	public void setPoliticalStatus(int politicalStatus) {
		jCpoliticalStatus.setSelectedIndex(politicalStatus);
	}

	@Override
	public void setStudentNo(String studentNo) {
		this.studentNo.setText(studentNo);
	}

	@Override
	public void setAddress(String address) {
		this.address.setText(address);
	}

	@Override
	public void setBirthplace(String birthplace) {
		this.birthplace.setText(birthplace);
	}

	@Override
	public void setClassId(String classId) {
		this.classId.setText(classId);
	}

	@Override
	public void setPhone(String phone) {
		this.phone.setText(phone);
	}

	@Override
	public void setPassowrd(String password) {
		text_password.setText(password);
	}

	@Override
	public void setStudentName(String name) {
		nameField.setText(name);
	}

	@Override
	public void setSpecializedSubject(String specializedSubject) {
		this.specializedSubject.setText(specializedSubject);
	}

}

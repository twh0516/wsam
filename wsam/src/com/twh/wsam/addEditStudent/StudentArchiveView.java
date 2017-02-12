package com.twh.wsam.addEditStudent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.twh.wsam.Numbering;
import com.twh.wsam.data.entity.CmdType;
import com.twh.wsam.data.entity.Student;
import com.twh.wsam.login.LoginPresenter;
import com.twh.wsam.util.AppUtil;

public class StudentArchiveView extends JPanel implements Numbering {
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField studentNo;
	private JTextField classId;
	private JTextField specializedSubject;
	private JTextField phone;
	private JTextField birthplace;
	private JTextField address;

	/**
	 * Create the panel.
	 */
	public StudentArchiveView(Frame parent) {
		setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		setLayout(gbl_panel);
		gbl_panel.columnWidths = new int[] { 80, 100, 100, 100 };
		gbl_panel.rowHeights = new int[] { 80, 60, 60, 60, 60, 60, 100 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gbl_panel);

		int top = 20;
		JLabel label = new JLabel("姓名：");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(top, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		add(label, gbc_label);

		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("宋体", Font.PLAIN, 20));
		nameField.setBackground(Color.WHITE);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.BOTH;
		gbc_nameField.insets = new Insets(top, 0, 5, 5);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 1;
		add(nameField, gbc_nameField);

		JLabel label_1 = new JLabel("学号：");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(top, 0, 5, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		add(label_1, gbc_label_1);

		studentNo = new JTextField();
		studentNo.setHorizontalAlignment(SwingConstants.CENTER);
		studentNo.setFont(new Font("宋体", Font.PLAIN, 20));
		studentNo.setBackground(Color.WHITE);
		GridBagConstraints gbc_studentNo = new GridBagConstraints();
		gbc_studentNo.fill = GridBagConstraints.BOTH;
		gbc_studentNo.insets = new Insets(top, 0, 5, 0);
		gbc_studentNo.gridx = 3;
		gbc_studentNo.gridy = 1;
		add(studentNo, gbc_studentNo);

		JLabel label_2 = new JLabel("班级：");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(top, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		add(label_2, gbc_label_2);

		classId = new JTextField();
		classId.setHorizontalAlignment(SwingConstants.CENTER);
		classId.setFont(new Font("宋体", Font.PLAIN, 20));
		classId.setBackground(Color.WHITE);
		GridBagConstraints gbc_classId = new GridBagConstraints();
		gbc_classId.fill = GridBagConstraints.BOTH;
		gbc_classId.insets = new Insets(top, 0, 5, 5);
		gbc_classId.gridx = 1;
		gbc_classId.gridy = 2;
		add(classId, gbc_classId);

		JLabel label_3 = new JLabel("专业：");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(top, 0, 5, 5);
		gbc_label_3.gridx = 2;
		gbc_label_3.gridy = 2;
		add(label_3, gbc_label_3);

		specializedSubject = new JTextField();
		specializedSubject.setHorizontalAlignment(SwingConstants.CENTER);
		specializedSubject.setFont(new Font("宋体", Font.PLAIN, 20));
		specializedSubject.setBackground(Color.WHITE);
		GridBagConstraints gbc_specializedSubject = new GridBagConstraints();
		gbc_specializedSubject.fill = GridBagConstraints.BOTH;
		gbc_specializedSubject.insets = new Insets(top, 0, 5, 0);
		gbc_specializedSubject.gridx = 3;
		gbc_specializedSubject.gridy = 2;
		add(specializedSubject, gbc_specializedSubject);

		JLabel label_4 = new JLabel("联系电话：");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(top, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 3;
		add(label_4, gbc_label_4);

		phone = new JTextField();
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setFont(new Font("宋体", Font.PLAIN, 20));
		phone.setBackground(Color.WHITE);
		GridBagConstraints gbc_phone = new GridBagConstraints();
		gbc_phone.fill = GridBagConstraints.BOTH;
		gbc_phone.insets = new Insets(top, 0, 5, 5);
		gbc_phone.gridx = 1;
		gbc_phone.gridy = 3;
		add(phone, gbc_phone);

		JLabel label_7 = new JLabel("政治面貌：");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.fill = GridBagConstraints.BOTH;
		gbc_label_7.insets = new Insets(top, 0, 5, 5);
		gbc_label_7.gridx = 2;
		gbc_label_7.gridy = 3;
		add(label_7, gbc_label_7);

		final JComboBox politicalStatus = new JComboBox();
		politicalStatus.setFont(new Font("宋体", Font.PLAIN, 20));
		politicalStatus.setBackground(Color.WHITE);
		GridBagConstraints gbc_politicalStatus = new GridBagConstraints();
		gbc_politicalStatus.insets = new Insets(top, 0, 5, 0);
		gbc_politicalStatus.fill = GridBagConstraints.BOTH;
		gbc_politicalStatus.gridx = 3;
		gbc_politicalStatus.gridy = 3;
		add(politicalStatus, gbc_politicalStatus);

		JLabel label_6 = new JLabel("家庭住址：");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.fill = GridBagConstraints.BOTH;
		gbc_label_6.insets = new Insets(top, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 4;
		add(label_6, gbc_label_6);

		address = new JTextField();
		address.setHorizontalAlignment(SwingConstants.CENTER);
		address.setFont(new Font("宋体", Font.PLAIN, 20));
		address.setBackground(Color.WHITE);
		GridBagConstraints gbc_address = new GridBagConstraints();
		gbc_address.gridwidth = 3;
		gbc_address.insets = new Insets(top, 0, 5, 0);
		gbc_address.fill = GridBagConstraints.BOTH;
		gbc_address.gridx = 1;
		gbc_address.gridy = 4;
		add(address, gbc_address);

		JLabel label_5 = new JLabel("籍贯：");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.BOTH;
		gbc_label_5.insets = new Insets(top, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 5;
		add(label_5, gbc_label_5);

		birthplace = new JTextField();
		birthplace.setHorizontalAlignment(SwingConstants.CENTER);
		birthplace.setFont(new Font("宋体", Font.PLAIN, 20));
		birthplace.setBackground(Color.WHITE);
		GridBagConstraints gbc_birthplace = new GridBagConstraints();
		gbc_birthplace.insets = new Insets(top, 0, 5, 0);
		gbc_birthplace.fill = GridBagConstraints.BOTH;
		gbc_birthplace.gridwidth = 3;
		gbc_birthplace.gridx = 1;
		gbc_birthplace.gridy = 5;
		add(birthplace, gbc_birthplace);

		JButton button = new JButton("保存档案");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				student.setAddress(address.getText().trim());
				student.setBirthplace(birthplace.getText().trim());
				student.setClassId(classId.getText().trim());
				student.setName(nameField.getText().trim());
				student.setPhone(phone.getText().trim());
				student.setPoliticalStatus(politicalStatus.getName());
				student.setSpecializedSubject(specializedSubject.getText());
				student.setStudentNo(studentNo.getText());
				student.setCmd(CmdType.STUDENT);
				String json = JSON.toJSONString(student);
				try {
					new Thread(new com.twh.wsam.netClient.TextNetClient(AppUtil.getServerIp(), AppUtil.getJsonPort(),
							json, new LoginPresenter())).start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 4;
		gbc_button.insets = new Insets(0, 0, 0, 40);
		gbc_button.gridx = 0;
		gbc_button.gridy = 6;
		add(button, gbc_button);
		button.setFont(new Font("宋体", Font.PLAIN, 20));

	}

	private static void createRoom() {
		JFrame frame = new JFrame("学生档案测试");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StudentArchiveView room = new StudentArchiveView(frame);
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
	public int getNo() {
		return 0;
	}

	@Override
	public void notify(String json) {
		JSONObject jsonObject = JSON.parseObject(json.trim());
		if (jsonObject.containsKey(com.twh.wsam.data.entity.CmdType.CMD)
				&& jsonObject.getString(com.twh.wsam.data.entity.CmdType.CMD).equals(CmdType.STUDENT)) {
			JOptionPane.showMessageDialog(this, json, "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

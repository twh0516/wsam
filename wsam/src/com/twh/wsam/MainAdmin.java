package com.twh.wsam;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.twh.wsam.addEditExaminationRoom.ExaminationRoomView;
import com.twh.wsam.addEditStudent.StudentArchiveView;
import com.twh.wsam.examination.SearchExaminationView;
import com.twh.wsam.examination.addEditExamination;
import com.twh.wsam.login.LoginDialog;
import com.twh.wsam.setting.SettingView;
import com.twh.wsam.teacher.TeacherPresenter;
import com.twh.wsam.teacher.AddEditTeacher;
import com.twh.wsam.teacher.TeacherContract.Presenter;

public class MainAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Color mouseEnteredColor = new Color(100, 149, 237);
	public static Color mouseExitedColor = new Color(0, 0, 0);

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String lookAt = "system";
		if (lookAt.equals("system")) {
			/* 系统风格 */
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (lookAt.equals("modif")) {
			/* sun自定义风格 */
			try {
				UIManager.setLookAndFeel("com.sun.java." + "swing.plaf.motif.MotifLookAndFeel");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (lookAt.equals("cross")) {
			/* 跨平台风格 */
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAdmin frame = new MainAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainAdmin() {
		setTitle(Appinfo.appName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1064, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);

		// 用于功能模块切换及效果
		int moduleCount = 3;
		final JLabel[] titles = new JLabel[moduleCount];
		final JPanel[] modules = new JPanel[moduleCount];
		final int addTeacherNo = 0;
		final int addRoomNo = 1;
		final int addEnvironmentNo = 2;
		// 添加各功能模块
		// 教师信息
		AddEditTeacher teacher = new AddEditTeacher();
		Presenter presenter = new TeacherPresenter(null);
		presenter.setView(teacher);
		teacher.setPresenter(presenter);
		teacher.start();
		
		teacher.setBackground(Color.WHITE);
		addModule(panel_1, sl_panel_1, teacher, modules,addTeacherNo);

		// 考场信息
		JPanel examinationRoom = new ExaminationRoomView();
		addModule(panel_1, sl_panel_1, examinationRoom, modules,addRoomNo);

		// 服务器配置
		JPanel serverConfig = new SettingView();
		addModule(panel_1, sl_panel_1, serverConfig, modules,addEnvironmentNo);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
					.addContainerGap())
		);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0 };
		gbl_panel.rowHeights = new int[] { 60, 60, 60, 60, 60, 60 };
		gbl_panel.columnWeights = new double[] { 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);
		
				JLabel label_teacher = new JLabel("教师信息 ");
				titles[addTeacherNo] = label_teacher;
				label_teacher.setOpaque(true);
				label_teacher.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						selectModule(titles, modules, addTeacherNo);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// label_3.setForeground(mouseEnteredColor);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// label_3.setForeground(mouseExitedColor);
					}
				});
				label_teacher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label_teacher.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_teacher = new GridBagConstraints();
				gbc_label_teacher.anchor = GridBagConstraints.NORTHEAST;
				gbc_label_teacher.insets = new Insets(0, 0, 5, 0);
				gbc_label_teacher.gridx = 0;
				gbc_label_teacher.gridy = 0;
				panel.add(label_teacher, gbc_label_teacher);
		
				JLabel label_room = new JLabel("创建考场 ");
				titles[addRoomNo] = label_room;
				label_room.setOpaque(true);
				label_room.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						selectModule(titles, modules, addRoomNo);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// label_2.setForeground(mouseEnteredColor);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// label_2.setForeground(mouseExitedColor);
					}
				});
				label_room.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label_room.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_room = new GridBagConstraints();
				gbc_label_room.anchor = GridBagConstraints.NORTHEAST;
				gbc_label_room.insets = new Insets(0, 0, 5, 0);
				gbc_label_room.gridx = 0;
				gbc_label_room.gridy = 1;
				panel.add(label_room, gbc_label_room);
		
				JLabel label_environment = new JLabel("环境搭建 ");
				titles[addEnvironmentNo] = label_environment;
				label_environment.setOpaque(true);
				label_environment.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						selectModule(titles, modules, addEnvironmentNo);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// label_4.setForeground(mouseEnteredColor);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// label_4.setForeground(mouseExitedColor);
					}
				});
				label_environment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label_environment.setFont(new Font("宋体", Font.PLAIN, 20));
				GridBagConstraints gbc_label_environment = new GridBagConstraints();
				gbc_label_environment.insets = new Insets(0, 0, 5, 0);
				gbc_label_environment.anchor = GridBagConstraints.NORTHEAST;
				gbc_label_environment.gridx = 0;
				gbc_label_environment.gridy = 2;
				panel.add(label_environment, gbc_label_environment);
		contentPane.setLayout(gl_contentPane);
	}

	private void selectModule(JLabel[] titles, JPanel[] modules, int moduleNo) {
		for (JLabel label : titles) {
			if (label != null) {
				label.setForeground(mouseExitedColor);
			}
		}
		for (JPanel panel : modules) {
			if (panel != null)
				panel.setVisible(false);
		}

		if (moduleNo < titles.length && titles[moduleNo] != null) {
			titles[moduleNo].setForeground(mouseEnteredColor);
		}
		if (moduleNo < modules.length && modules[moduleNo] != null) {
			modules[moduleNo].setVisible(true);
		}
	}

	private void addModule(JPanel panel_1, SpringLayout sl_panel_1, JPanel module, JPanel[] modules,int no) {
		sl_panel_1.putConstraint(SpringLayout.EAST, module, 710, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, module, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, module, 30, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, module, 550, SpringLayout.NORTH, panel_1);
		module.setVisible(false);
		panel_1.add(module);
		if (no < modules.length)
			modules[no] = module;
	}
}

package com.twh.wsam;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
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
import com.twh.wsam.examination.SearchExamination;
import com.twh.wsam.examination.AddEditExamination;
import com.twh.wsam.setting.AddSetting;
import com.twh.wsam.student.AddStudentContract;
import com.twh.wsam.student.AddStudentPresenter;
import com.twh.wsam.student.StudentArchive;
import com.twh.wsam.teacher.AddEditTeacher;
import com.twh.wsam.util.AppUtil;

public class MainStudent extends JFrame {

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

		// try {
		// javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels =
		// javax.swing.UIManager
		// .getInstalledLookAndFeels();
		// for (int idx = 0; idx < installedLookAndFeels.length; idx++)
		// if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
		// javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
		// break;
		// }
		// } catch (ClassNotFoundException ex) {
		// java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE,
		// null, ex);
		// } catch (InstantiationException ex) {
		// java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE,
		// null, ex);
		// } catch (IllegalAccessException ex) {
		// java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE,
		// null, ex);
		// } catch (javax.swing.UnsupportedLookAndFeelException ex) {
		// java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE,
		// null, ex);
		// }

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainStudent frame = new MainStudent();
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
	public MainStudent() {
		setTitle(Appinfo.appName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 680);
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
		int moduleCount = 2;
		final JLabel[] titles = new JLabel[moduleCount];
		final JPanel[] modules = new JPanel[moduleCount];
		final int studentTitleNo = 0;
		final int examintaionTitleNo = 1;
		// 添加各功能模块
		// 学员建档
		StudentArchive sv = new StudentArchive(this);
		addModule(panel_1, sl_panel_1, sv, modules, studentTitleNo);
		addModule(panel_1, sl_panel_1, sv, modules,studentTitleNo);
		AddStudentContract.Presenter addStudentPresenter = new AddStudentPresenter(AppUtil.cStudent);
		sv.setPresenter(addStudentPresenter);
		addStudentPresenter.setView(sv);
		sv.start();

		// 考试查询
		JPanel search = new SearchExamination(this);
		addModule(panel_1, sl_panel_1, search, modules,examintaionTitleNo);

		sv.setVisible(true);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(22)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE).addGap(47)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(82)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 550,
										Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 547,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		JLabel label = new JLabel("个人档案");
		titles[studentTitleNo] = label;
		label.setOpaque(true);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, studentTitleNo);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// label.setForeground(mouseEnteredColor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// label.setForeground(mouseExitedColor);
			}

		});
		label.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_1 = new JLabel("考试查询");
		titles[examintaionTitleNo] = label_1;
		label_1.setOpaque(true);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, examintaionTitleNo);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// label_1.setForeground(mouseEnteredColor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// label_1.setForeground(mouseExitedColor);
			}
		});
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(41, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label))
					.addGap(35))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(73)
					.addComponent(label)
					.addGap(45)
					.addComponent(label_1)
					.addContainerGap(381, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
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
		sl_panel_1.putConstraint(SpringLayout.NORTH, module, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, module, 30, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, module, 550, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, module, 720, SpringLayout.WEST, panel_1);
		module.setVisible(false);
		panel_1.add(module);
		if (no < modules.length)
			modules[no] = module;
	}
}

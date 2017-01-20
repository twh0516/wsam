package com.twh.wsam;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.twh.wsam.addEditExamination.ExaminationJPanel;
import com.twh.wsam.addEditExaminationRoom.ExaminationRoomView;
import com.twh.wsam.addEditStudent.StudentArchiveView;
import com.twh.wsam.addEditTeacher.TeacherView;
import com.twh.wsam.login.LoginDialog;
import com.twh.wsam.searchExamination.SearchExaminationView;
import com.twh.wsam.setting.SettingView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainView extends JFrame {

	public static Color mouseEnteredColor = new Color(100, 149, 237);
	public static Color mouseExitedColor = new Color(0, 0, 0);
	private LoginDialog dialog;

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
					MainView frame = new MainView();
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
	public MainView() {
		setTitle(Appinfo.appName);
		dialog = new LoginDialog(this);
		dialog.setVisible(true);
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
		int moduleCount = 6;
		final JLabel[] titles = new JLabel[moduleCount];
		final JPanel[] modules = new JPanel[moduleCount];

		// 添加各功能模块
		// 学员建档
		JPanel sv = new StudentArchiveView(this);
		addModule(panel_1, sl_panel_1, sv, modules);

		// 考试查询
		JPanel search = new SearchExaminationView(this);
		addModule(panel_1, sl_panel_1, search, modules);

		// 教师信息
		JPanel teacher = new TeacherView();
		addModule(panel_1, sl_panel_1, teacher, modules);

		// 考场信息
		JPanel examinationRoom = new ExaminationRoomView();
		addModule(panel_1, sl_panel_1, examinationRoom, modules);

		// 服务器配置
		JPanel serverConfig = new SettingView();
		addModule(panel_1, sl_panel_1, serverConfig, modules);

		// 新建考试
		JPanel addExamination = new ExaminationJPanel();
		addModule(panel_1, sl_panel_1, addExamination, modules);

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
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0 };
		gbl_panel.rowHeights = new int[] { 60, 60, 60, 60, 60, 60 };
		gbl_panel.columnWeights = new double[] { 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);

		JLabel label = new JLabel("学员建档");
		titles[0] = label;
		label.setOpaque(true);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, 0);
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
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);

		JLabel label_5 = new JLabel("新建考试");
		titles[5] = label_5;
		label_5.setOpaque(true);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.NORTH;
		gbc_label_5.insets = new Insets(0, 0, 5, 0);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 1;
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, 5);
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
		panel.add(label_5, gbc_label_5);

		JLabel label_1 = new JLabel("考试查询");
		titles[1] = label_1;
		label_1.setOpaque(true);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, 1);
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
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.NORTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		panel.add(label_1, gbc_label_1);

		JLabel label_2 = new JLabel("创建考场 ");
		titles[2] = label_2;
		label_2.setOpaque(true);
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, 2);
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
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		panel.add(label_2, gbc_label_2);

		JLabel label_3 = new JLabel("教师信息 ");
		titles[3] = label_3;
		label_3.setOpaque(true);
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, 3);
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
		label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 4;
		panel.add(label_3, gbc_label_3);

		JLabel label_4 = new JLabel("  环境搭建 ");
		titles[4] = label_4;
		label_4.setOpaque(true);
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, 4);
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
		label_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 0);
		gbc_label_4.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 5;
		panel.add(label_4, gbc_label_4);
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

	private void addModule(JPanel panel_1, SpringLayout sl_panel_1, JPanel module, JPanel[] modules) {
		sl_panel_1.putConstraint(SpringLayout.NORTH, module, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, module, 30, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, module, 550, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, module, 720, SpringLayout.WEST, panel_1);
		module.setVisible(false);
		panel_1.add(module);
		int no = ((Numbering) module).getNo();
		if (no < modules.length)
			modules[no] = module;
	}
}

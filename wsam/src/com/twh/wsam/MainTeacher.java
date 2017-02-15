package com.twh.wsam;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
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

import com.twh.wsam.examination.SearchExamination;
import com.twh.wsam.examination.SearchExaminationContract;
import com.twh.wsam.examination.SearchExaminationPresenter;
import com.twh.wsam.examination.AddEditExamination;
import com.twh.wsam.examination.AddExaminationContract;
import com.twh.wsam.examination.AddExaminationPresenter;
import com.twh.wsam.student.AddStudentContract;
import com.twh.wsam.student.AddStudentPresenter;
import com.twh.wsam.student.SearchStudent;
import com.twh.wsam.student.SearchStudentContract;
import com.twh.wsam.student.SearchStudentPresenter;
import com.twh.wsam.student.StudentArchive;
import com.twh.wsam.teacher.AddEditTeacher;
import com.twh.wsam.teacher.AddTeacherContract.Presenter;
import com.twh.wsam.teacher.AddTeacherPresenter;
import com.twh.wsam.util.AppUtil;

public class MainTeacher extends JFrame {

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
					MainTeacher frame = new MainTeacher();
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
	public MainTeacher() {
		setTitle(Appinfo.appName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 680);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-getHeight())/2;
		setLocation(x, y);
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
		int moduleCount = 5;
		final JLabel[] titles = new JLabel[moduleCount];
		final JPanel[] modules = new JPanel[moduleCount];
		final int studentNo = 0;
		final int searchNo = 1;
		final int teacherNo = 2;
		final int addExaminationNo = 3;
		final int searchStudentNo = 4;
		// 添加各功能模块
		// 学员建档
		StudentArchive sv = new StudentArchive(this);
		AddStudentContract.Presenter addStudentPresenter = new AddStudentPresenter(null);
		sv.setPresenter(addStudentPresenter);
		addStudentPresenter.setView(sv);
		addModule(panel_1, sl_panel_1, sv, modules, studentNo);

		// 考试查询
		SearchExamination search = new SearchExamination(this);
		SearchExaminationContract.Presenter searchExaPresenter = new SearchExaminationPresenter();
		search.setPresenter(searchExaPresenter);
		searchExaPresenter.setView(search);
		addModule(panel_1, sl_panel_1, search, modules, searchNo);

		// 教师信息
		AddEditTeacher teacher = new AddEditTeacher();
		Presenter presenter = new AddTeacherPresenter(AppUtil.cTeacher);
		presenter.setView(teacher);
		teacher.setPresenter(presenter);
		teacher.start();
		addModule(panel_1, sl_panel_1, teacher, modules, teacherNo);

		// 新建考试
		AddEditExamination addExamination = new AddEditExamination();
		AddExaminationContract.Presenter addExaPresenter = new AddExaminationPresenter(null);
		addExamination.setPresenter(addExaPresenter);
		addExaPresenter.setView(addExamination);
		addExamination.start();
		addModule(panel_1, sl_panel_1, addExamination, modules, addExaminationNo);
		
		SearchStudent searchStudent = new SearchStudent();
		addModule(panel_1, sl_panel_1, searchStudent, modules, searchStudentNo);
		SearchStudentContract.Presenter searchStudentPresenter = new SearchStudentPresenter();
		searchStudent.setPresenter(searchStudentPresenter);
		searchStudentPresenter.setView(searchStudent);
		

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
		gbl_panel.rowHeights = new int[] { 120, 60, 60, 60, 60, 0 };
		gbl_panel.columnWeights = new double[] { 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel label_addArchive = new JLabel("学员建档");
		titles[studentNo] = label_addArchive;
		label_addArchive.setOpaque(true);
		label_addArchive.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_addArchive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, studentNo);
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
		label_addArchive.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_addArchive = new GridBagConstraints();
		gbc_label_addArchive.fill = GridBagConstraints.VERTICAL;
		gbc_label_addArchive.insets = new Insets(80, 0, 5, 0);
		gbc_label_addArchive.gridx = 0;
		gbc_label_addArchive.gridy = 0;
		panel.add(label_addArchive, gbc_label_addArchive);

		JLabel label_searchExamination = new JLabel("考试查询");
		titles[searchNo] = label_searchExamination;
		label_searchExamination.setOpaque(true);
		label_searchExamination.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, searchNo);
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

		JLabel label_addExamination = new JLabel("新建考试");
		titles[addExaminationNo] = label_addExamination;
		label_addExamination.setOpaque(true);
		label_addExamination.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_addExamination = new GridBagConstraints();
		gbc_label_addExamination.fill = GridBagConstraints.VERTICAL;
		gbc_label_addExamination.insets = new Insets(20, 0, 5, 0);
		gbc_label_addExamination.gridx = 0;
		gbc_label_addExamination.gridy = 2;
		label_addExamination.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_addExamination.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, addExaminationNo);
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

		JLabel label_searchStudent = new JLabel("学员查询");
		titles[searchStudentNo] = label_searchStudent;
		label_searchStudent.setOpaque(true);
		label_searchStudent.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_searchStudent = new GridBagConstraints();
		gbc_label_searchStudent.fill = GridBagConstraints.VERTICAL;
		gbc_label_searchStudent.insets = new Insets(20, 0, 5, 0);
		gbc_label_searchStudent.gridx = 0;
		gbc_label_searchStudent.gridy = 1;
		panel.add(label_searchStudent, gbc_label_searchStudent);
		label_searchStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_searchStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, searchStudentNo);
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
		
		
		panel.add(label_addExamination, gbc_label_addExamination);
		label_searchExamination.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_searchExamination.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_label_searchExamination = new GridBagConstraints();
		gbc_label_searchExamination.fill = GridBagConstraints.VERTICAL;
		gbc_label_searchExamination.insets = new Insets(20, 0, 5, 0);
		gbc_label_searchExamination.gridx = 0;
		gbc_label_searchExamination.gridy = 3;
		panel.add(label_searchExamination, gbc_label_searchExamination);

		JLabel label_teacher = new JLabel("教师信息 ");
		titles[teacherNo] = label_teacher;
		label_teacher.setOpaque(true);
		label_teacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectModule(titles, modules, teacherNo);
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
		gbc_label_teacher.insets = new Insets(20, 0, 5, 0);
		gbc_label_teacher.fill = GridBagConstraints.VERTICAL;
		gbc_label_teacher.anchor = GridBagConstraints.EAST;
		gbc_label_teacher.gridx = 0;
		gbc_label_teacher.gridy = 4;
		panel.add(label_teacher, gbc_label_teacher);
		contentPane.setLayout(gl_contentPane);
	}

	private void selectModule(JLabel[] titles, JPanel[] modules, int moduleNo) {
		// 取消选定全部
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

	private void addModule(JPanel panel_1, SpringLayout sl_panel_1, JPanel module, JPanel[] modules, int no) {
		sl_panel_1.putConstraint(SpringLayout.NORTH, module, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, module, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, module, 550, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, module, 780, SpringLayout.WEST, panel_1);
		module.setVisible(false);
		panel_1.add(module);
		if (no < modules.length)
			modules[no] = module;
	}
}

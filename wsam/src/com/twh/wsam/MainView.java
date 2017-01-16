package com.twh.wsam;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.twh.wsam.addEditStudent.StudentArchiveView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		StudentArchiveView sv = new StudentArchiveView();
		sl_panel_1.putConstraint(SpringLayout.NORTH, sv, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, sv, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, sv, 630, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, sv, 1258, SpringLayout.WEST, panel_1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1664, Short.MAX_VALUE)
					.addGap(32))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(82)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 727, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		panel_1.add(sv);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel label = new JLabel("学员建档");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(sv.isVisible())
					sv.setVisible(false);
				else
					sv.setVisible(true);
			}
		});
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		sl_panel.putConstraint(SpringLayout.NORTH, label, 79, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, label, -39, SpringLayout.EAST, panel);
		panel.add(label);
		
		JLabel label_1 = new JLabel("学员查询");
		sl_panel.putConstraint(SpringLayout.NORTH, label_1, 45, SpringLayout.SOUTH, label);
		sl_panel.putConstraint(SpringLayout.EAST, label_1, 0, SpringLayout.EAST, label);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("创建考场 ");
		sl_panel.putConstraint(SpringLayout.NORTH, label_2, 44, SpringLayout.SOUTH, label_1);
		sl_panel.putConstraint(SpringLayout.WEST, label_2, 0, SpringLayout.WEST, label);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("用户信息 ");
		sl_panel.putConstraint(SpringLayout.NORTH, label_3, 42, SpringLayout.SOUTH, label_2);
		sl_panel.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, label);
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("服务器配置");
		sl_panel.putConstraint(SpringLayout.NORTH, label_4, 33, SpringLayout.SOUTH, label_3);
		sl_panel.putConstraint(SpringLayout.EAST, label_4, 0, SpringLayout.EAST, label);
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(label_4);
		contentPane.setLayout(gl_contentPane);
	}
}

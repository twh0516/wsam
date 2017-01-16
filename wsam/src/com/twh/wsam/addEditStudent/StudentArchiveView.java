package com.twh.wsam.addEditStudent;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StudentArchiveView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> textField_7;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentArchiveView frame = new StudentArchiveView();
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
	public StudentArchiveView() {
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1116, 676);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel label_1 = new JLabel("\u5B66\u5458\u5EFA\u6863");
		sl_contentPane.putConstraint(SpringLayout.WEST, label_1, 448, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, label_1, -513, SpringLayout.SOUTH, contentPane);
		label_1.setFont(new Font("宋体", Font.BOLD, 35));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBackground(SystemColor.activeCaption);
		contentPane.add(label_1);
		
		JPanel JPane = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, JPane, 26, SpringLayout.SOUTH, label_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, JPane, 170, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, JPane, -157, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, JPane, -189, SpringLayout.EAST, contentPane);
		JPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		JPane.setBackground(Color.WHITE);
		contentPane.add(JPane);
		JPane.setLayout(new GridLayout(0, 4, 0, 20));
		
		JLabel label_2 = new JLabel("\u59D3\u540D\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_2);
		
		txtName = new JTextField();
		txtName.setFont(new Font("宋体", Font.PLAIN, 20));
		txtName.setBackground(Color.WHITE);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		JPane.add(txtName);
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBackground(Color.WHITE);
		JPane.add(textField_1);
		
		JLabel label_5 = new JLabel("\u73ED\u7EA7\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_5);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBackground(Color.WHITE);
		JPane.add(textField);
		
		JLabel label_6 = new JLabel("\u4E13\u4E1A\uFF1A");
		JPane.add(label_6);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_6, 10, SpringLayout.SOUTH, JPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_6, 99, SpringLayout.WEST, contentPane);
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setBackground(Color.WHITE);
		JPane.add(textField_2);
		
		JLabel label_7 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_7);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_3.setBackground(Color.WHITE);
		JPane.add(textField_3);
		
		JLabel label_3 = new JLabel("\u7C4D\u8D2F\uFF1A");
		JPane.add(label_3);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_3, 66, SpringLayout.SOUTH, JPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_3, 217, SpringLayout.WEST, contentPane);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_4.setBackground(Color.WHITE);
		JPane.add(textField_4);
		
		JLabel label_8 = new JLabel("\u5BB6\u5EAD\u4F4F\u5740\uFF1A");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_8);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_5.setBackground(Color.WHITE);
		JPane.add(textField_5);
		
		JLabel label_10 = new JLabel("\u653F\u6CBB\u9762\u8C8C\uFF1A");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_10);
		textField_7 = new JComboBox();
		textField_7.addItem("团员");
		textField_7.addItem("党员");
		textField_7.addItem("群众");
		
//		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_7.setBackground(Color.WHITE);
		JPane.add(textField_7);
		
		JLabel label_11 = new JLabel("\u8003\u573A\u53F7\uFF1A");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_11);
		
		JComboBox comboBox = new JComboBox();
		JPane.add(comboBox);
		
		JLabel label_9 = new JLabel("\u5DE5\u4F4D\u53F7\uFF1A");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_9);
		
		JComboBox comboBox_1 = new JComboBox();
		JPane.add(comboBox_1);
		
		JLabel label_4 = new JLabel("\u8003\u8BD5\u65E5\u671F\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_4);
		
		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_9.setBackground(Color.WHITE);
		JPane.add(textField_9);
		
		JLabel label_12 = new JLabel("\u5F00\u59CB\u65F6\u95F4\uFF1A");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_12);
		
		textField_10 = new JTextField();
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_10.setBackground(Color.WHITE);
		JPane.add(textField_10);
		
		JLabel label_13 = new JLabel("\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setFont(new Font("宋体", Font.PLAIN, 20));
		JPane.add(label_13);
		
		textField_11 = new JTextField();
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_11.setBackground(Color.WHITE);
		JPane.add(textField_11);
		
		JButton button = new JButton("保存");
		button.setFont(new Font("宋体", Font.PLAIN, 25));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, button, -60, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, button, -471, SpringLayout.EAST, contentPane);
		contentPane.add(button);
		
		
	}
}

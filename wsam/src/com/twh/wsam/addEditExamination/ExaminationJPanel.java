package com.twh.wsam.addEditExamination;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.twh.wsam.Numbering;

import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.ComponentOrientation;

public class ExaminationJPanel extends JPanel implements Numbering{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JButton button_1;
	private JButton button_2;
	private JLabel label_1;
	private JTextPane comment;
	private JButton button_3;
	private JLabel label_2;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		try {
			ExaminationJPanel dialog = new ExaminationJPanel();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ExaminationJPanel() {
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBackground(Color.WHITE);
		setBounds(100, 100, 714, 655);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{70, 45, 35, 35, 35, 35, 35,35,80,80};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0};
		int top = 10;
		setLayout(gridBagLayout);
		{
			JLabel label = new JLabel("李勇的测评", SwingConstants.CENTER);
			label.setFont(new Font("宋体", Font.BOLD, 25));
			label.setBackground(SystemColor.activeCaption);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.HORIZONTAL;
			gbc_label.insets = new Insets(0, 0, 5, 0);
			gbc_label.gridwidth = 4;
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			add(label, gbc_label);
		}
		{
			JLabel label = new JLabel("姓名：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.fill = GridBagConstraints.VERTICAL;
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 1;
			add(label, gbc_label);
		}
		{
			textField_5 = new JTextField();
			GridBagConstraints gbc_textField_5 = new GridBagConstraints();
			gbc_textField_5.insets = new Insets(top, 0, 5, 5);
			gbc_textField_5.fill = GridBagConstraints.BOTH;
			gbc_textField_5.gridx = 1;
			gbc_textField_5.gridy = 1;
			add(textField_5, gbc_textField_5);
		}
		{
			JLabel label = new JLabel("学号：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 1;
			add(label, gbc_label);
		}
		{
			textField_6 = new JTextField();
			GridBagConstraints gbc_textField_6 = new GridBagConstraints();
			gbc_textField_6.insets = new Insets(top, 0, 5, 5);
			gbc_textField_6.fill = GridBagConstraints.BOTH;
			gbc_textField_6.gridx = 3;
			gbc_textField_6.gridy = 1;
			add(textField_6, gbc_textField_6);
		}
		{
			JLabel label = new JLabel("考场号：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 2;
			add(label, gbc_label);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(top, 0, 5, 5);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 2;
			add(comboBox, gbc_comboBox);
		}
		{
			textField_4 = new JTextField();
			textField_4.setHorizontalAlignment(SwingConstants.CENTER);
			textField_4.setFont(new Font("宋体", Font.PLAIN, 20));
			textField_4.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 5);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 3;
			gbc_textField_4.gridy = 4;
			add(textField_4, gbc_textField_4);
		}
		{
			JLabel label = new JLabel("全景视频：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 5;
			add(label, gbc_label);
		}
		{
			JLabel label = new JLabel("普通视频：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 5;
			add(label, gbc_label);
		}
		{
			JButton button = new JButton();
			button.setText("点击播放视频");
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setFont(new Font("宋体", Font.PLAIN, 20));
			button.setBackground(Color.WHITE);
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.fill = GridBagConstraints.HORIZONTAL;
			gbc_button.anchor = GridBagConstraints.NORTH;
			gbc_button.insets = new Insets(10, 0, 5, 5);
			gbc_button.gridx = 3;
			gbc_button.gridy = 5;
			add(button, gbc_button);
		}
		
		
		{
			JLabel label = new JLabel("专业等级：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 4;
			add(label, gbc_label);
		}
		{
			JLabel label = new JLabel("开始时间：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 3;
			add(label, gbc_label);
		}
		{
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("宋体", Font.PLAIN, 20));
			textField.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.anchor = GridBagConstraints.NORTH;
			gbc_textField.insets = new Insets(10, 0, 5, 5);
			gbc_textField.gridx = 3;
			gbc_textField.gridy = 3;
			add(textField, gbc_textField);
		}
		{
			JLabel label = new JLabel("结束时间：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 4;
			add(label, gbc_label);
		}
		
		{
			JButton button = new JButton();
			button.setText("点击获取视频");
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setFont(new Font("宋体", Font.PLAIN, 20));
			button.setBackground(Color.WHITE);
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.fill = GridBagConstraints.HORIZONTAL;
			gbc_button.anchor = GridBagConstraints.NORTH;
			gbc_button.insets = new Insets(top, 0, 5, 5);
			gbc_button.gridx = 1;
			gbc_button.gridy = 5;
			add(button, gbc_button);
		}
		{
			textField_1 = new JTextField();
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
			textField_1.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.anchor = GridBagConstraints.NORTH;
			gbc_textField_1.insets = new Insets(top, 0, 5, 5);
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 4;
			add(textField_1, gbc_textField_1);
		}
		{
			JLabel label = new JLabel("考试日期：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 3;
			add(label, gbc_label);
		}
		
		{
			textField_3 = new JTextField();
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.fill = GridBagConstraints.BOTH;
			gbc_textField_3.insets = new Insets(top, 0, 5, 5);
			gbc_textField_3.gridx = 1;
			gbc_textField_3.gridy = 3;
			add(textField_3, gbc_textField_3);
		}
		{
			JLabel label = new JLabel("工位号：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHEAST;
			gbc_label.insets = new Insets(top, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 2;
			add(label, gbc_label);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
			comboBox.setBackground(Color.WHITE);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.anchor = GridBagConstraints.NORTH;
			gbc_comboBox.insets = new Insets(10, 0, 5, 5);
			gbc_comboBox.gridx = 3;
			gbc_comboBox.gridy = 2;
			add(comboBox, gbc_comboBox);
		}
		{
			button_1 = new JButton("重新获取全景视频");
			button_1.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_button_1 = new GridBagConstraints();
			gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_button_1.anchor = GridBagConstraints.NORTH;
			gbc_button_1.insets = new Insets(top, 0, 5, 5);
			gbc_button_1.gridx = 1;
			gbc_button_1.gridy = 6;
			add(button_1, gbc_button_1);
		}
		{
			button_2 = new JButton("重新获取普通视频");
			button_2.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_button_2 = new GridBagConstraints();
			gbc_button_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_button_2.anchor = GridBagConstraints.NORTH;
			gbc_button_2.insets = new Insets(10, 0, 5, 5);
			gbc_button_2.gridx = 3;
			gbc_button_2.gridy = 6;
			add(button_2, gbc_button_2);
		}
		{
			label_2 = new JLabel("评分：");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label_2 = new GridBagConstraints();
			gbc_label_2.anchor = GridBagConstraints.EAST;
			gbc_label_2.fill = GridBagConstraints.VERTICAL;
			gbc_label_2.insets = new Insets(top, 0, 5, 5);
			gbc_label_2.gridx = 0;
			gbc_label_2.gridy = 7;
			add(label_2, gbc_label_2);
		}
		{
			textField_2 = new JTextField();
			textField_2.setHorizontalAlignment(SwingConstants.CENTER);
			textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
			textField_2.setBackground(Color.WHITE);
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.insets = new Insets(top, 0, 5, 5);
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 7;
			add(textField_2, gbc_textField_2);
		}
		{
			label_1 = new JLabel("评语：");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setFont(new Font("宋体", Font.PLAIN, 20));
			GridBagConstraints gbc_label_1 = new GridBagConstraints();
			gbc_label_1.anchor = GridBagConstraints.NORTHEAST;
			gbc_label_1.insets = new Insets(top, 0, 5, 5);
			gbc_label_1.gridx = 0;
			gbc_label_1.gridy = 8;
			add(label_1, gbc_label_1);
		}
		
		comment = new JTextPane();
		comment.setFont(new Font("宋体", Font.PLAIN, 20));
		JScrollPane scrollPane = new JScrollPane(comment);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(10, 0, 5, 5);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 8;
		add(scrollPane, gbc_scrollPane);
		{
			button_3 = new JButton("保存");
			button_3.setFont(new Font("宋体", Font.PLAIN, 25));
			GridBagConstraints gbc_button_3 = new GridBagConstraints();
			gbc_button_3.insets = new Insets(0, 0, 0, 5);
			gbc_button_3.gridwidth = 4;
			gbc_button_3.gridx = 0;
			gbc_button_3.gridy = 9;
			add(button_3, gbc_button_3);
		}
		{
		}
	}

	@Override
	public int getNo() {
		return 5;
	}
}

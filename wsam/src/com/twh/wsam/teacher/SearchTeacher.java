package com.twh.wsam.teacher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.teacher.SearchTeacherContract.Presenter;
import com.twh.wsam.teacher.SearchTeacherContract.View;
import java.awt.SystemColor;

public class SearchTeacher extends JPanel implements View{
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;
	private String[] searchFields = new String[] {"姓名", "工号"};
	private DefaultTableModel model = new DefaultTableModel();
	private String[] columnNames = {"姓名","工号"};
	private JComboBox comboBox = new JComboBox();
	/**
	 * Create the panel.
	 */
	public SearchTeacher() {
		setBackground(new Color(255, 255, 255));
		JPanel search = new JPanel();
		search.setBackground(Color.WHITE);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		table.setFont(new Font("宋体", Font.PLAIN, 20));//表格内容字体
		table.setBackground(SystemColor.control);
		table.setPreferredScrollableViewportSize(new Dimension(300, 70));
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20));//表头字体
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.WHITE);
		
		
		model.setDataVector(null, columnNames);
		table.setModel(model);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, comboBox, 55, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, comboBox, 48, SpringLayout.WEST, panel);
		panel.setLayout(sl_panel);
		
		textField = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textField, 43, SpringLayout.EAST, comboBox);
		sl_panel.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, textField);
		sl_panel.putConstraint(SpringLayout.NORTH, textField, 55, SpringLayout.NORTH, panel);
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		panel.add(textField);
		
		JButton button = new JButton("查询");
		sl_panel.putConstraint(SpringLayout.EAST, textField, -96, SpringLayout.WEST, button);
		sl_panel.putConstraint(SpringLayout.WEST, button, 471, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, button, 54, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, button, -82, SpringLayout.EAST, panel);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cmd;
				if(comboBox.getSelectedIndex() == 0) {
					cmd = CmdType.TEACHER_SEARCH_NAME;
				}else {
					cmd = CmdType.TEACHER_SEARCH_NO;
				}
				presenter.searchTeacher(textField.getText(), cmd);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button);
		comboBox.setModel(new DefaultComboBoxModel(searchFields));
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(comboBox);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(135, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		GroupLayout gl_search = new GroupLayout(search);
		gl_search.setHorizontalGroup(
			gl_search.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_search.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_search.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, Alignment.LEADING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		gl_search.setVerticalGroup(
			gl_search.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_search.createSequentialGroup()
					.addGap(41)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		search.setLayout(gl_search);
		setLayout(groupLayout);
		

	}
	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	@Override
	public void start() {
		
	}
	private Presenter presenter;
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	@Override
	public void showResultTable(String[][] data) {
		model.setDataVector(data, columnNames);
	}

}

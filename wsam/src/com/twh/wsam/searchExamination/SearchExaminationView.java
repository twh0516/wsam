package com.twh.wsam.searchExamination;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import com.twh.wsam.Numbering;
import com.twh.wsam.addEditExamination.ExaminationJPanel;

import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchExaminationView extends JPanel implements Numbering{
	private JTable table;
	private JTextField textField_1;
	MyDialog addExamDialog ;
	
	private class MyDialog extends JDialog {

		public MyDialog(Frame parent) {
			super(parent, "新建考试");
			setContentPane(new ExaminationJPanel());
			setBounds(100, 100, 800, 655);
		}
		
	}
	/**
	 * Create the panel.
	 */
	public SearchExaminationView(Frame parent) {
		addExamDialog = new MyDialog(parent);
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[929px]", "[30px][144px][336px]"));
		
		JLabel label = new JLabel("查询学员考试信息", SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 25));
		label.setBackground(SystemColor.activeCaption);
		add(label, "cell 0 0,alignx center,aligny top");
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(table.getSelectedRow() + "");
				addExamDialog.setVisible(true);
			}
		});
		table.setFont(new Font("宋体", Font.PLAIN, 20));//表格内容字体
		table.setBackground(new Color(255, 255, 255));
		table.setPreferredScrollableViewportSize(new Dimension(300, 70));
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20));//表头字体
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, "cell 0 2,grow");
		final DefaultTableModel model = new DefaultTableModel();
		final Object data[][] = {{"高级班","李勇","2017","2016-05-08"},
				{"高级班","李勇","2017","2016-05-08"},
				{"高级班","李勇","2017","2016-05-08"},
				{"高级班","李勇","2017","2016-05-08"},
				{"高级班","李勇","2017","2016-05-08"},
				{"高级班","李勇","2017","2016-05-08"},
				{"高级班","李勇","2017","2016-05-08"},
				{"高级班","李勇","2017","2016-05-08"},
				{"高级班","李勇","2017","2016-05-08"},
				{"高级班","王志和","2015","2016-07-08"},
				{"高级班","王志和","2015","2016-07-08"},
				{"高级班","王志和","2015","2016-07-08"},
				{"高级班","王志和","2015","2016-07-08"},
				{"高级班","王志和","2015","2016-07-08"},
				{"高级班","王志和","2015","2016-07-08"},
				{"高级班","王志和","2015","2016-07-08"},
				{"高级班","王志和","2015","2016-07-08"},
				{"高级班","王志和","2015","2016-07-08"}};
		final String[] columnNames = {"班级","姓名","学号","考试时间"};
		
		model.setDataVector(null, columnNames);
		table.setModel(model);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 1,grow");
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		textField_1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textField_1, 55, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField_1, -56, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textField_1, -325, SpringLayout.EAST, panel);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		JButton button = new JButton("查询");
		sl_panel.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, textField_1);
		sl_panel.putConstraint(SpringLayout.WEST, button, 69, SpringLayout.EAST, textField_1);
		sl_panel.putConstraint(SpringLayout.EAST, button, -117, SpringLayout.EAST, panel);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setDataVector(data, columnNames);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button);
		
		JComboBox comboBox = new JComboBox();
		sl_panel.putConstraint(SpringLayout.WEST, textField_1, 40, SpringLayout.EAST, comboBox);
		sl_panel.putConstraint(SpringLayout.EAST, comboBox, -542, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, comboBox, 1, SpringLayout.NORTH, textField_1);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"姓名", "学号", "考试时间"}));
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(comboBox);
		

	}

	@Override
	public int getNo() {
		return 1;
	}
}

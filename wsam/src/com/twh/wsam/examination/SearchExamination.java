package com.twh.wsam.examination;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import com.twh.util.calendar.CalendarChooser;
import com.twh.util.calendar.TimeChooserDialog;
import com.twh.wsam.Numbering;
import com.twh.wsam.cmd.CmdType;
import com.twh.wsam.examination.SearchExaminationContract.Presenter;
import com.twh.wsam.examination.SearchExaminationContract.View;

import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class SearchExamination extends JPanel implements View{
	private JTable table;
	private JTextField text_time;
	MyDialog addExamDialog ;
	private Presenter presenter;
	JComboBox comboBox = new JComboBox();
	JButton btn_search = new JButton("查询");
	private JTextField text_name;
	private DefaultTableModel model;
	private Object data[][];
	private String[] columnNames = {"姓名","学号","考场","工位","考试日期"};
	private String[] menus = {"姓名", "学号", "考试时间"};
	private JTextField text_date;
	private class MyDialog extends JDialog {

		public MyDialog(Frame parent) {
			super(parent, "新建考试");
			setContentPane(new AddEditExamination());
			setBounds(100, 100, 800, 655);
		}
		
	}
	/**
	 * Create the panel.
	 */
	public SearchExamination(Frame parent) {
		addExamDialog = new MyDialog(parent);
		setBackground(Color.WHITE);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(data[table.getSelectedRow()][3]);
				addExamDialog.setVisible(true);
			}
		});
		table.setFont(new Font("宋体", Font.PLAIN, 20));//表格内容字体
		table.setBackground(new Color(255, 255, 255));
		table.setPreferredScrollableViewportSize(new Dimension(300, 70));
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20));//表头字体
		
		JPanel panel_body = new JPanel();
		JScrollPane scrollPane = new JScrollPane(table);
		model = new DefaultTableModel();
		
		model.setDataVector(null, columnNames);
		table.setModel(model);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		comboBox.setModel(new DefaultComboBoxModel(menus));
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()== ItemEvent.SELECTED) {
					String item = (String) e.getItem();
					if(item.equals(menus[2])) {
						text_time.setVisible(true);
						text_date.setVisible(true);
						text_name.setVisible(false);
						 SwingUtilities.updateComponentTreeUI(SearchExamination.this);
					}else {
						text_name.setVisible(true);
						text_time.setVisible(false);
						text_date.setVisible(false);
						SwingUtilities.updateComponentTreeUI(SearchExamination.this);
					}
				}
			}
		});
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(comboBox);
		
		text_name = new JTextField();
		text_name.setFont(new Font("宋体", Font.PLAIN, 20));
		text_name.setColumns(10);
		panel.add(text_name);
		
		text_time = new JTextField();
		TimeChooserDialog timeChooserDialog = TimeChooserDialog.getInstance("请选择考试时间");
		timeChooserDialog.register(text_time);
		text_time.setVisible(false);
		
		text_date = new JTextField();
		text_date.setVisible(false);
		CalendarChooser calendarChooser = CalendarChooser.getInstance();
		calendarChooser.register(text_date);
		text_date.setFont(new Font("宋体", Font.PLAIN, 20));
		text_date.setColumns(10);
		panel.add(text_date);
		text_time.setFont(new Font("宋体", Font.PLAIN, 20));
		text_time.setColumns(10);
		panel.add(text_time);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(panel_body, GroupLayout.PREFERRED_SIZE, 804, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(panel_body, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		GroupLayout gl_panel_body = new GroupLayout(panel_body);
		gl_panel_body.setHorizontalGroup(
			gl_panel_body.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_body.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel_body.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		gl_panel_body.setVerticalGroup(
			gl_panel_body.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_body.createSequentialGroup()
					.addGap(22)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
		);
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cmd;
				switch (comboBox.getSelectedIndex()) {
				case 0:
					cmd = CmdType.EXAMINATION_GET_NAME;
					presenter.searchExamination(text_name.getText(), text_time.getText(), cmd);
					break;
				case 1:
					cmd = CmdType.EXAMINATION_GET_NO;
					presenter.searchExamination(text_name.getText(), text_time.getText(), cmd);
					break;
				default:
					cmd = CmdType.EXAMINATION_GET_TIME;
					presenter.searchExamination(text_date.getText(), text_time.getText(), cmd);
					break;
				}
				
			}
		});
		btn_search.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(btn_search);
		panel_body.setLayout(gl_panel_body);
		setLayout(groupLayout);
	}
	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	@Override
	public void start() {
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	@Override
	public void showResultTable(String[][] data) {
		this.data = data;
		model.setDataVector(data, columnNames);
	}
}

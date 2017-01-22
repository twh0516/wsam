package com.twh.wsam;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TableTest extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableTest frame = new TableTest();
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
	public TableTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 566);
		DefaultTableModel dm = new DefaultTableModel();
		Object data[][] = {{"田文海","男","38","软件工程师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"},
				{"李素霞","女","31","视觉设计师"}};
		String[] columnNames = {"姓名","性别","年龄","职业"};
		dm.setDataVector(data, columnNames);
		JTable table = new JTable(dm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		table.setTableHeader(new JTableHeader());
		
	}

}

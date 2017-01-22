package com.twh.wsam;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.twh.learnJTable.Gui;

public class TableLearn extends JPanel {
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public TableLearn() {
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		DefaultTableModel model = new DefaultTableModel();
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
		
		model.setDataVector(data, columnNames);
		table.setModel(model);
	}
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Gui");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		TableLearn newContentPane = new TableLearn();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}

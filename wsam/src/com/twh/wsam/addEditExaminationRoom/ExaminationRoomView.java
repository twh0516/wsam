package com.twh.wsam.addEditExaminationRoom;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.twh.wsam.Numbering;
import com.twh.wsam.data.entity.Camera;
import com.twh.wsam.data.entity.Station;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.Rectangle;
import javax.swing.ScrollPaneConstants;
import java.awt.Point;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExaminationRoomView extends JPanel implements Numbering {
	private JTextField textField;
	private int countIP = 1, countStation = 1;// TODO test

	/**
	 * Create the panel.
	 */
	public ExaminationRoomView() {
		setLayout(new BorderLayout(0, 0));
		setFont(new Font("宋体", Font.PLAIN, 20));
		final DefaultMutableTreeNode root = new DefaultMutableTreeNode("考场");
		// Station station = new Station();
		// station.setNo("1号");
		// DefaultMutableTreeNode sNode = new DefaultMutableTreeNode(station);
		// Camera camera = new Camera();
		// camera.setIp("192.168.1.30");
		// DefaultMutableTreeNode cNode = new DefaultMutableTreeNode(camera);
		// sNode.add(cNode);
		// root.add(sNode);
		final DefaultTreeModel model = new DefaultTreeModel(root);
		final JTree tree = new JTree(model);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {

			}
		});
		tree.setFont(new Font("宋体", Font.PLAIN, 20));
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane, BorderLayout.CENTER);

		JPanel panelTitle = new JPanel();
		JLabel label_2 = new JLabel("考场编号：");
		label_2.setOpaque(true);
		label_2.setBackground(new Color(204, 204, 204));
		label_2.setFont(new Font("宋体", Font.PLAIN, 25));
		panelTitle.add(label_2);
		add(panelTitle, BorderLayout.NORTH);

		textField = new JTextField();
		textField.setToolTipText("请在此输入考场编号");
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		panelTitle.add(textField);
		textField.setColumns(10);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("添加工位");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("工位_" + countStation++);
				model.insertNodeInto(newNode, root, root.getChildCount());
				// now display new node

				TreeNode[] nodes = model.getPathToRoot(newNode);
				TreePath path = new TreePath(nodes);
				tree.scrollPathToVisible(path);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(btnNewButton);

		JButton button = new JButton("添加摄像头");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode == null)
					return;

				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("192.168.1." + countIP++);
				newNode.setAllowsChildren(false);
				model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
				// now display new node

				TreeNode[] nodes = model.getPathToRoot(newNode);
				for (TreeNode node : nodes) {
					System.out.println(node);
				}
				TreePath path = new TreePath(nodes);
				tree.scrollPathToVisible(path);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button);

		JButton button_1 = new JButton("保存考场信息");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

				if (selectedNode != null && selectedNode.getParent() != null)
					model.removeNodeFromParent(selectedNode);
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button_2);
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button_1);

	}

	private static void createRoom() {
		JFrame frame = new JFrame("创建考场");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ExaminationRoomView room = new ExaminationRoomView();
		frame.getContentPane().add(room, BorderLayout.CENTER);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createRoom();
			}
		});
	}

	@Override
	public int getNo() {
		// TODO Auto-generated method stub
		return 2;
	}
}

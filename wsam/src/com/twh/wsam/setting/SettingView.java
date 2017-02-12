package com.twh.wsam.setting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.twh.wsam.Numbering;
import com.twh.wsam.addEditExaminationRoom.ExaminationRoomView;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SettingView extends JPanel implements Numbering{
	private static final long serialVersionUID = 1L;
	private int countIP = 1, countStation = 1;// TODO test

	/**
	 * Create the panel.
	 */
	public SettingView() {
		setBackground(Color.WHITE);
		JPanel settingPanel = new JPanel();
		settingPanel.setLayout(new BorderLayout(0, 0));
		setFont(new Font("宋体", Font.PLAIN, 20));
		final DefaultMutableTreeNode root = new DefaultMutableTreeNode("环境配置");
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
		settingPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelTitle = new JPanel();
		JLabel label_2 = new JLabel("环境配置");
		label_2.setToolTipText("绑定摄像机硬盘录像机");
		label_2.setOpaque(true);
		label_2.setBackground(SystemColor.controlHighlight);
		label_2.setFont(new Font("宋体", Font.PLAIN, 25));
		panelTitle.add(label_2);
		settingPanel.add(panelTitle, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		settingPanel.add(panel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("添加录像机");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("录像机_" + countStation++);
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

				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("摄像机_192.168.1." + countIP++);
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

		JButton button_1 = new JButton("保存");
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
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(settingPanel, GroupLayout.PREFERRED_SIZE, 584, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(350, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(settingPanel, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	private static void createRoom() {
		JFrame frame = new JFrame("环境配置");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SettingView room = new SettingView();
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
		return 4;
	}

}

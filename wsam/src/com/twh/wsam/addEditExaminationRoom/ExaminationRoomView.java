package com.twh.wsam.addEditExaminationRoom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.sun.org.apache.bcel.internal.ExceptionConstants;
import com.twh.util.string.StringUtil;
import com.twh.wsam.addEditExaminationRoom.ExaminationRoomContract.Presenter;
import com.twh.wsam.data.entity.Camera;
import com.twh.wsam.data.entity.ExaminationRoom;
import com.twh.wsam.data.entity.Station;
import com.twh.wsam.data.entity.VCR;
import com.twh.wsam.setting.VCRDialog;
import com.twh.wsam.util.AppUtil;

public class ExaminationRoomView extends JPanel implements ExaminationRoomContract.View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExaminationRoomContract.Presenter presenter;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JTree tree;

	/**
	 * Create the panel.
	 */
	public ExaminationRoomView() {
		setBackground(Color.WHITE);
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new BorderLayout(0, 0));
		setFont(new Font("宋体", Font.PLAIN, 20));
		root = new DefaultMutableTreeNode("考场管理");
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setRowHeight(AppUtil.treeRowHeight);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {

			}
		});
		tree.setFont(new Font("宋体", Font.PLAIN, 20));
		JScrollPane scrollPane = new JScrollPane(tree);
		roomPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelTitle = new JPanel();
		roomPanel.add(panelTitle, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		roomPanel.add(panel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("添加工位");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode == null || selectedNode.getParent() == null) {
					showMessage("请选中一个考场！");
					return;
				}
				if (!selectedNode.getAllowsChildren()) {
					showMessage("不能在摄像头中添加工位！");
					return;
				}
				if (selectedNode.getUserObject() instanceof Station) {
					showMessage("不能在工位中添加工位！");
					return;
				}
				ExaminationRoom room = (ExaminationRoom) selectedNode.getUserObject();
				String stationNo = JOptionPane.showInputDialog("请输入工位号", "001");
				if (StringUtil.isEmpty(stationNo)) {
					return;
				}
				Station station = new Station();
				station.setNo(stationNo);
				station.setRoomNo(room.getRoomNo());
				boolean isOk = presenter.addStation(station, room);
				if (isOk) {
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(station);
					model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
					// now display new node

					TreeNode[] nodes = model.getPathToRoot(newNode);
					TreePath path = new TreePath(nodes);
					tree.scrollPathToVisible(path);
				} else {
					showMessage("工位" + station.getNo() + " 已存在!");
				}
			}
		});

		JButton button_3 = new JButton("添加考场");
		button_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String roomNo = JOptionPane.showInputDialog("请输入考场号", "A001");
				if (StringUtil.isEmpty(roomNo)) {
					return;
				}
				ExaminationRoom room = new ExaminationRoom();
				room.setRoomNo(roomNo);
				if (presenter.addRoom(room)) {
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(room);
					model.insertNodeInto(newNode, root, root.getChildCount());

					// 移动到新添加的元素
					TreeNode[] nodes = model.getPathToRoot(newNode);
					TreePath path = new TreePath(nodes);
					tree.scrollPathToVisible(path);
				} else {
					showMessage("考场" + room.getRoomNo() + "已存在!");
				}
			}
		});
		button_3.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button_3);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(btnNewButton);

		JButton button = new JButton("添加摄像头");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VCR[] vcrs = presenter.getVCRs();
				if (vcrs == null) {
					showMessage("请先做环境配置，如果已做配置请稍候再试！");
					return;
				}
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode == null || selectedNode.getParent() == null) {
					showMessage("请选中一个工位！");
					return;
				}
				if (!selectedNode.getAllowsChildren()) {
					showMessage("不能在摄像头中添加摄像头！");
					return;
				}
				if (selectedNode.getUserObject() instanceof ExaminationRoom) {
					showMessage("请选中要添加摄像头的工位!");
					return;
				}
				Station station = (Station) selectedNode.getUserObject();
				String filter = "滤镜摄像头", normal = "全景摄像头";
				Object[] selections = { filter, normal };
				Object val = JOptionPane.showInputDialog(ExaminationRoomView.this, "请选择摄像头类型", "选择",
						JOptionPane.INFORMATION_MESSAGE, null, selections, selections[0]);
				boolean isFilter = false;
				if (val != null) {
					String sel = val.toString();
					if (sel.equals(filter)) {
						isFilter = true;
					} else {
						isFilter = false;
					}

				} else {
					showMessage("摄像头类型是必选项！");
					return;
				}
				val = JOptionPane.showInputDialog(ExaminationRoomView.this, "请选择硬盘录像机", "选择",
						JOptionPane.INFORMATION_MESSAGE, null, vcrs, vcrs[0]);
				if (val != null) {
					VCR vcr = (VCR) val;
					List<Camera> list_camera = vcr.getList();
					if (list_camera == null) {
						showMessage("该硬盘未绑定摄像头！");
						return;
					} else {
						Camera[] cameras = new Camera[list_camera.size()];
						cameras = list_camera.toArray(cameras);

						Object oCamera = JOptionPane.showInputDialog(ExaminationRoomView.this, "请选择摄像头所在通道", "选择",
								JOptionPane.INFORMATION_MESSAGE, null, cameras, cameras[0]);
						if (oCamera != null) {
							Camera camera = (Camera) oCamera;
							boolean isOk = presenter.addCamera(station, isFilter, camera);
							if (isOk) {
								
								DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(camera);
								newNode.setAllowsChildren(false);
								model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
								// now display new node

								TreeNode[] nodes = model.getPathToRoot(newNode);
								TreePath path = new TreePath(nodes);
								tree.scrollPathToVisible(path);
							} else {
								return;
							}
						} else {
							showMessage("您未选择摄像头所在通道！");
							return;
						}

					}
				} else {
					showMessage("您未选择硬盘录像机！");
					return;
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button);

		JButton button_1 = new JButton("保存");
		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				presenter.submitRooms();
			}
		});

		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null && selectedNode.getParent() != null) {
					Object obj = selectedNode.getUserObject();
					if (obj instanceof ExaminationRoom) {
						ExaminationRoom room = (ExaminationRoom) obj;
						presenter.removeRoom(room);
					}else if(obj instanceof Station){
						Station station = (Station) obj;
						DefaultMutableTreeNode stationNode = (DefaultMutableTreeNode) selectedNode.getParent();
						ExaminationRoom room = (ExaminationRoom) stationNode.getUserObject();
						presenter.removeStation(room,station);
					} else if (obj instanceof Camera) {
						Camera camera = (Camera) obj;
						DefaultMutableTreeNode stationNode = (DefaultMutableTreeNode) selectedNode.getParent();
						Station station = (Station) stationNode.getUserObject();
						presenter.removeCamera(station,camera);
					}
					model.removeNodeFromParent(selectedNode);
				}else {
					showMessage("请选中要删除选项！");
				}
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button_2);
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(button_1);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(roomPanel, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(191, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(roomPanel, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(185, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	private static void createRoom() {
		JFrame frame = new JFrame("考场管理");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ExaminationRoomView room = new ExaminationRoomView();
		frame.getContentPane().add(room, BorderLayout.CENTER);
		frame.setSize(800, 700);
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
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void start() {
		presenter.start();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void showSettings(List<ExaminationRoom> list) {
		TreeNode[] nodes;
		TreePath path;
		for (ExaminationRoom room : list) {
			DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode(room);
			model.insertNodeInto(roomNode, root, root.getChildCount());
			nodes = model.getPathToRoot(roomNode);
			path = new TreePath(nodes);
			tree.scrollPathToVisible(path);
			List<Station> stations = room.getList();
			if (stations != null) {
				for (Station station : stations) {
					DefaultMutableTreeNode stationNode = new DefaultMutableTreeNode(station);
					model.insertNodeInto(stationNode, roomNode, roomNode.getChildCount());
					nodes = model.getPathToRoot(stationNode);
					path = new TreePath(nodes);
					tree.scrollPathToVisible(path);
					
					if(!StringUtil.isEmpty(station.getFilterCameraIp())) {
						Camera filterCamera = new Camera();
						filterCamera.setIp_VCR(station.getFilterCameraIp());
						filterCamera.setChannel(station.getFilterCameraChannel());
						filterCamera.setFilter("滤镜");
						DefaultMutableTreeNode filterNode = new DefaultMutableTreeNode(filterCamera);
						model.insertNodeInto(filterNode, stationNode, stationNode.getChildCount());
						nodes = model.getPathToRoot(filterNode);
						path = new TreePath(nodes);
						tree.scrollPathToVisible(path);
					}
					if(!StringUtil.isEmpty(station.getNormalCameraIp())) {
						Camera normalCamera = new Camera();
						normalCamera.setIp_VCR(station.getNormalCameraIp());
						normalCamera.setChannel(station.getNormalCameraChannel());
						normalCamera.setNormal("全景");
						DefaultMutableTreeNode normalNode = new DefaultMutableTreeNode(normalCamera);
						model.insertNodeInto(normalNode, stationNode, stationNode.getChildCount());
						nodes = model.getPathToRoot(normalNode);
						path = new TreePath(nodes);
						tree.scrollPathToVisible(path);
					}
				}
			}
		}
	}

}

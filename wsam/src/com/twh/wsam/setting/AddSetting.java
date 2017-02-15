package com.twh.wsam.setting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import com.twh.util.LoggerUtil;
import com.twh.util.string.StringUtil;
import com.twh.wsam.data.entity.Camera;
import com.twh.wsam.data.entity.VCR;
import com.twh.wsam.setting.AddSettingContract.Presenter;
import com.twh.wsam.setting.AddSettingContract.View;
import com.twh.wsam.util.AppUtil;

public class AddSetting extends JPanel implements View {
	private static final long serialVersionUID = 1L;
	private int countIP = 1, countStation = 1;// TODO test
	private Presenter presenter;
	private VCRContractor.Presenter vcrPresenter;
	private VCRDialog dialog;
	private DefaultMutableTreeNode root ;
	private DefaultTreeModel model;
	private JTree tree ;
	
	/**
	 * Create the panel.
	 */
	public AddSetting() {
		setBackground(Color.WHITE);
		JPanel settingPanel = new JPanel();
		settingPanel.setLayout(new BorderLayout(0, 0));
		setFont(new Font("宋体", Font.PLAIN, 20));
		root = new DefaultMutableTreeNode("环境配置");
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
		JButton addVCRButton = new JButton("添加录像机");
		addVCRButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dialog == null) {
					dialog = new VCRDialog();
					vcrPresenter.setView(dialog);
					dialog.setPresenter(vcrPresenter);
				}
				VCR vcr = dialog.getResult();
				if(vcr == null)
					return;
				if(presenter.addVCR(vcr)) {
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(vcr);
					model.insertNodeInto(newNode, root, root.getChildCount());
	
					// 移动到新添加的元素
					TreeNode[] nodes = model.getPathToRoot(newNode);
					TreePath path = new TreePath(nodes);
					tree.scrollPathToVisible(path);
				}else {
					showMessage("录像机"+vcr.getIp()+"已存在!");
				}
			}
		});
		addVCRButton.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(addVCRButton);

		JButton addChannelButton = new JButton("添加通道");
		addChannelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode == null){
					showMessage("请选中一个录像机！");
					return;
				}
				if(selectedNode.getParent() == null) {
					showMessage("请选中一个录像机！");
					return;
				}
				if (!selectedNode.getAllowsChildren()) {
					showMessage("通道中不能再添加通道！");
					return;
				}
				String channel = JOptionPane.showInputDialog("请输入通道号", 0);
				if(StringUtil.isEmpty(channel)) {
					return;
				}
				Camera camera = new Camera();
				try {
					camera.setChannel(Integer.parseInt(channel));
				} catch (NumberFormatException e1) {
					showMessage("通道号只能是正整数！");
					return;
				}
				if (camera.getChannel() < 0) {
					showMessage("通道号只能是正整数！");
					return;
				}
				VCR vcr = (VCR) selectedNode.getUserObject();
				camera.setIp_VCR(vcr.getIp());
				// if(LoggerUtil.isDebug)
				// System.out.println(camera.getIp_VCR());
				boolean isOk = presenter.addCamera(vcr, camera.getChannel());
				if (isOk) {
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(camera);
					newNode.setAllowsChildren(false);
					model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
					// now display new node

					TreeNode[] nodes = model.getPathToRoot(newNode);
					TreePath path = new TreePath(nodes);
					tree.scrollPathToVisible(path);
				} else {
					showMessage("通道"+camera.getChannel()+" 已存在!");
				}
			}
		});
		addChannelButton.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(addChannelButton);

		JButton saveBtn = new JButton("保存");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.submitSettings();
			}
		});

		JButton deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode != null && selectedNode.getParent() != null) {
					Object obj = selectedNode.getUserObject();
					if (obj instanceof VCR) {
						VCR vcr = (VCR) obj;
						presenter.removeVCR(vcr.getIp());
					} else if (obj instanceof Camera) {
						Camera camera = (Camera) obj;
						presenter.removeCamera(camera.getIp_VCR(), camera.getChannel());
					}
					model.removeNodeFromParent(selectedNode);
				}else {
					showMessage("请选中要删除的录像机或通道！");
				}
			}
		});
		deleteBtn.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(deleteBtn);
		saveBtn.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(saveBtn);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(settingPanel, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(268, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(5)
						.addComponent(settingPanel, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(115, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	private static void createRoom() {
		JFrame frame = new JFrame("环境配置");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AddSetting room = new AddSetting();
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
	public void showSettings(List<VCR> list) {
		TreeNode[] nodes;
		TreePath path;
		for(VCR vcr : list) {
			DefaultMutableTreeNode vcrNode = new DefaultMutableTreeNode(vcr);
			model.insertNodeInto(vcrNode, root, root.getChildCount());
			nodes = model.getPathToRoot(vcrNode);
			path = new TreePath(nodes);
			tree.scrollPathToVisible(path);
			List<Camera> cameras = vcr.getList();
			if(cameras != null) {
				for(Camera camera : cameras) {
					DefaultMutableTreeNode cameraNode = new DefaultMutableTreeNode(camera);
					cameraNode.setAllowsChildren(false);
					model.insertNodeInto(cameraNode, vcrNode, vcrNode.getChildCount());
					nodes = model.getPathToRoot(cameraNode);
					path = new TreePath(nodes);
					tree.scrollPathToVisible(path);
				}
			}
		}
	}

	@Override
	public void setVCRPresenter(com.twh.wsam.setting.VCRContractor.Presenter presenter) {
		this.vcrPresenter = presenter;
	}

}

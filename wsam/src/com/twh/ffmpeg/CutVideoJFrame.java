package com.twh.ffmpeg;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.twh.util.LoggerUtil;
import com.twh.util.Util;
import com.twh.util.PrintStream.Callback;
import com.twh.util.WindowsDivers;
import com.twh.util.WindowsSysOperat;
import com.twh.wsam.util.AppUtil;

public class CutVideoJFrame extends JFrame implements Callback {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textInputname;
	private JTextField textOutputName;
	private JTextField textLastTime;
	private JTextField textBeginTime;
	private JLabel text_error;
	private String videoFormat;
	private String outVideoFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CutVideoJFrame frame = new CutVideoJFrame();
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
	public CutVideoJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btn_input = new JButton("\u9009\u62E9\u8F93\u5165\u89C6\u9891");
		btn_input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = jfc.showDialog(new JLabel(), "选择视频");
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					textInputname.setText(file.getAbsolutePath());
				}
			}
		});

		textInputname = new JTextField();
		textInputname.setColumns(10);

		textOutputName = new JTextField();
		textOutputName.setText(WindowsDivers.getMaxDriveString() + "/wsam/");
		textOutputName.setColumns(10);

		JButton btn_output = new JButton("\u9009\u62E9\u8F93\u51FA\u76EE\u5F55");
		btn_output.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = jfc.showDialog(new JLabel(), "输出目录");
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					textOutputName.setText(file.getAbsolutePath());
				}
			}	
		});

		textLastTime = new JTextField();
		textLastTime.setColumns(10);

		textBeginTime = new JTextField();
		textBeginTime.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_1 = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label = new JLabel("\u5206\u949F");
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label_1 = new JLabel("\u5206\u949F");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);

		JButton button = new JButton("\u5F00\u59CB\u526A\u5207");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_error.setVisible(false);
				String inputFile = textInputname.getText();
				String outputPath = textOutputName.getText();
				String beginTime = textBeginTime.getText();
				String lastTime = textLastTime.getText();
				videoFormat = Util.getExtensionName(inputFile);
				if (videoFormat == null) {
					text_error.setText("请选择视频");
					text_error.setVisible(true);
					return;
				}
				if (AppUtil.isEmpty(outputPath)) {
					text_error.setText("请设置视频输出路径");
					text_error.setVisible(true);
					return;
				} else {
					LoggerUtil.getLoger().info(outputPath);
				}
				if (AppUtil.isEmpty(beginTime)) {
					text_error.setText("请输入开始时间");
					text_error.setVisible(true);
					return;
				} else {
					try {
						LoggerUtil.getLoger().info(beginTime);
						beginTime = AppUtil.getFFmpegTime(beginTime);
					} catch (NumberFormatException ne) {
						text_error.setText("时间格式不正确");
					}
				}
				if (AppUtil.isEmpty(lastTime)) {
					text_error.setText("请设置视频持续时间");
					text_error.setVisible(true);
					return;
				} else {
					try {
						LoggerUtil.getLoger().info(beginTime);
						lastTime = AppUtil.getFFmpegTime(lastTime);
					} catch (NumberFormatException ne) {
						text_error.setText("时间格式不正确");
					}
				}
				
				VideoOperate.getInstance().cutVideo(beginTime, lastTime, AppUtil.getFFmpeg(), inputFile, outputPath,
						"输出视频." + videoFormat, CutVideoJFrame.this);
				outVideoFile = outputPath + "输出视频." + videoFormat;
			}
		});

		text_error = new JLabel();
		text_error.setForeground(Color.RED);
		text_error.setVisible(false);

		btn_player = new JButton("\u64AD\u653E\u89C6\u9891");
		btn_player.setVisible(false);
		btn_player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(outVideoFile == null){
					text_error.setVisible(true);
					text_error.setText("视频不存在");
				}else {
					//调用系统默认播放器播放视频
					WindowsSysOperat.openWindowsDirectoryOrFile(outVideoFile);
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn_input, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn_output, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(65)
				.addGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING,
												false)
										.addComponent(textInputname)
										.addComponent(textOutputName, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(textBeginTime, GroupLayout.PREFERRED_SIZE, 157,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textLastTime, GroupLayout.PREFERRED_SIZE, 157,
														GroupLayout.PREFERRED_SIZE))
										.addGap(27)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 93,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 93,
														GroupLayout.PREFERRED_SIZE))))
				.addGap(123))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(147).addComponent(button).addGap(72)
						.addComponent(btn_player).addContainerGap(197, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(97)
						.addComponent(text_error, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE).addGap(322)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btn_input).addComponent(
						textInputname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btn_output).addComponent(
						textOutputName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(31)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textBeginTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel).addComponent(label))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textLastTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
						.addComponent(lblNewLabel_1))
				.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE).addComponent(text_error).addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(button)
						.addComponent(btn_player, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(71)));
		contentPane.setLayout(gl_contentPane);
	}

	int count = 0;
	private JButton btn_player;

	@Override
	public void updateState(final boolean isFinish) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!isFinish) {
					text_error.setVisible(true);
					int index = count / 20;
					text_error.setText("正在剪切" + show[index]);
					count++;
					if (count >= 60)
						count = 0;
				} else {
					text_error.setVisible(true);
					text_error.setText("剪切成功");
					btn_player.setVisible(true);
				}
			}
		});
	}

}

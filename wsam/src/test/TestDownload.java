package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alibaba.fastjson.JSONObject;
import com.twh.wsam.DownloadClient;
import com.twh.wsam.DownloadClient.Callback;
import com.twh.wsam.util.AppUtil;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;

public class TestDownload extends JFrame implements Callback{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestDownload frame = new TestDownload();
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
	public TestDownload() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton button = new JButton("点击下载");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JSONObject json = new JSONObject();
					json.put("fileName", "F:/movie/奇幻森林.BD1280高清国英双语中英双字.mp4");
					new Thread(new DownloadClient(AppUtil.getServerIp().trim(), AppUtil.getDownloadPort(), json.toString(), TestDownload.this)).start();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(button);
	}

	@Override
	public void notify(String json) {
		JOptionPane.showMessageDialog(this, json, "Hey!", JOptionPane.INFORMATION_MESSAGE);
	}
	

}

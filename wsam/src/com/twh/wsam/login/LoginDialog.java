package com.twh.wsam.login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginDialog extends JDialog {
	private JTextField textField;
	private JPasswordField textField_1;
	private JButton okButton;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog(Frame parent) {
		super(parent,"登录");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 675, 540);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.CENTER);
			SpringLayout sl_buttonPane = new SpringLayout();
			buttonPane.setLayout(sl_buttonPane);
			{
				okButton = new JButton("登录");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				sl_buttonPane.putConstraint(SpringLayout.NORTH, okButton, 392, SpringLayout.NORTH, buttonPane);
				sl_buttonPane.putConstraint(SpringLayout.WEST, okButton, 199, SpringLayout.WEST, buttonPane);
				sl_buttonPane.putConstraint(SpringLayout.SOUTH, okButton, -61, SpringLayout.SOUTH, buttonPane);
				sl_buttonPane.putConstraint(SpringLayout.EAST, okButton, -215, SpringLayout.EAST, buttonPane);
				okButton.setFont(new Font("宋体", Font.PLAIN, 20));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			JLabel label = new JLabel("用户名：");
			sl_buttonPane.putConstraint(SpringLayout.NORTH, label, 219, SpringLayout.NORTH, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, okButton);
			sl_buttonPane.putConstraint(SpringLayout.EAST, label, -378, SpringLayout.EAST, buttonPane);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			buttonPane.add(label);
			
			textField = new JTextField();
			sl_buttonPane.putConstraint(SpringLayout.NORTH, textField, 258, SpringLayout.NORTH, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, okButton);
			sl_buttonPane.putConstraint(SpringLayout.EAST, textField, -215, SpringLayout.EAST, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, label, -6, SpringLayout.NORTH, textField);
			buttonPane.add(textField);
			textField.setColumns(10);
			
			JLabel label_1 = new JLabel("密码：");
			sl_buttonPane.putConstraint(SpringLayout.NORTH, label_1, 292, SpringLayout.NORTH, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, okButton);
			sl_buttonPane.putConstraint(SpringLayout.EAST, label_1, -394, SpringLayout.EAST, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, textField, -1, SpringLayout.NORTH, label_1);
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setFont(new Font("宋体", Font.PLAIN, 20));
			buttonPane.add(label_1);
			
			textField_1 = new JPasswordField();
			sl_buttonPane.putConstraint(SpringLayout.NORTH, textField_1, 331, SpringLayout.NORTH, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, okButton);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, textField_1, -28, SpringLayout.NORTH, okButton);
			sl_buttonPane.putConstraint(SpringLayout.EAST, textField_1, -215, SpringLayout.EAST, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, label_1, -6, SpringLayout.NORTH, textField_1);
			textField_1.setColumns(10);
			buttonPane.add(textField_1);
			
			JLabel label_2 = new JLabel("端口号：");
			sl_buttonPane.putConstraint(SpringLayout.WEST, label_2, 0, SpringLayout.WEST, okButton);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, label_2, -53, SpringLayout.NORTH, label);
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setFont(new Font("宋体", Font.PLAIN, 20));
			buttonPane.add(label_2);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
			textField_2.setText("8001");
			sl_buttonPane.putConstraint(SpringLayout.NORTH, textField_2, 6, SpringLayout.SOUTH, label_2);
			sl_buttonPane.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, okButton);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, textField_2, -14, SpringLayout.NORTH, label);
			sl_buttonPane.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, okButton);
			textField_2.setColumns(10);
			buttonPane.add(textField_2);
			
			JLabel lblip = new JLabel("服务器ip:");
			sl_buttonPane.putConstraint(SpringLayout.WEST, lblip, 0, SpringLayout.WEST, okButton);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, lblip, -49, SpringLayout.NORTH, label_2);
			lblip.setHorizontalAlignment(SwingConstants.RIGHT);
			lblip.setFont(new Font("宋体", Font.PLAIN, 20));
			buttonPane.add(lblip);
			
			textField_3 = new JTextField();
			sl_buttonPane.putConstraint(SpringLayout.NORTH, textField_3, -39, SpringLayout.NORTH, label_2);
			sl_buttonPane.putConstraint(SpringLayout.WEST, textField_3, 0, SpringLayout.WEST, okButton);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, textField_3, -6, SpringLayout.NORTH, label_2);
			sl_buttonPane.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, okButton);
			textField_3.setColumns(10);
			buttonPane.add(textField_3);
		}
	}
}

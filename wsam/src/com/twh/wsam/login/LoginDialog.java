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
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setBounds(100, 100, 606, 432);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.CENTER);
			SpringLayout sl_buttonPane = new SpringLayout();
			buttonPane.setLayout(sl_buttonPane);
			{
				JButton okButton = new JButton("登录");
				okButton.setFont(new Font("宋体", Font.PLAIN, 20));
				sl_buttonPane.putConstraint(SpringLayout.WEST, okButton, 233, SpringLayout.WEST, buttonPane);
				sl_buttonPane.putConstraint(SpringLayout.SOUTH, okButton, -57, SpringLayout.SOUTH, buttonPane);
				okButton.setVerticalAlignment(SwingConstants.BOTTOM);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			JLabel label = new JLabel("用户名：");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			sl_buttonPane.putConstraint(SpringLayout.NORTH, label, 108, SpringLayout.NORTH, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.WEST, label, 151, SpringLayout.WEST, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, label, -247, SpringLayout.SOUTH, buttonPane);
			sl_buttonPane.putConstraint(SpringLayout.EAST, label, -357, SpringLayout.EAST, buttonPane);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			buttonPane.add(label);
			
			textField = new JTextField();
			sl_buttonPane.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, label);
			sl_buttonPane.putConstraint(SpringLayout.WEST, textField, 13, SpringLayout.EAST, label);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, label);
			sl_buttonPane.putConstraint(SpringLayout.EAST, textField, -170, SpringLayout.EAST, buttonPane);
			buttonPane.add(textField);
			textField.setColumns(10);
			
			JLabel label_1 = new JLabel("密码：");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			sl_buttonPane.putConstraint(SpringLayout.NORTH, label_1, 40, SpringLayout.SOUTH, label);
			sl_buttonPane.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, label);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, label_1, 73, SpringLayout.SOUTH, label);
			sl_buttonPane.putConstraint(SpringLayout.EAST, label_1, 0, SpringLayout.EAST, label);
			label_1.setFont(new Font("宋体", Font.PLAIN, 20));
			buttonPane.add(label_1);
			
			textField_1 = new JTextField();
			sl_buttonPane.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, label_1);
			sl_buttonPane.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
			sl_buttonPane.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, label_1);
			sl_buttonPane.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
			textField_1.setColumns(10);
			buttonPane.add(textField_1);
		}
	}
}

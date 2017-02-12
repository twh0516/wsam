package com.twh.wsam;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.twh.wsam.login.LoginDialog;
import com.twh.wsam.login.LoginContract.Presenter;
import com.twh.wsam.login.LoginPresenter;

public class Start {
	public static void main(String args[]) {
		String lookAt = "system";
		if (lookAt.equals("system")) {
			/* 系统风格 */
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (lookAt.equals("modif")) {
			/* sun自定义风格 */
			try {
				UIManager.setLookAndFeel("com.sun.java." + "swing.plaf.motif.MotifLookAndFeel");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (lookAt.equals("cross")) {
			/* 跨平台风格 */
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presenter presenter = new LoginPresenter();
					LoginDialog loginDialog = new LoginDialog(presenter);
					presenter.setView(loginDialog);
					loginDialog.start();
					int type = loginDialog.getResult();
					switch (type) {
					case -1:
						break;
					case 0://管理员
						MainAdmin admin = new MainAdmin();
						admin.setVisible(true);
						break;
					case 1://老师
						MainTeacher teacher = new MainTeacher();
						teacher.setVisible(true);
						break;
					default://学员
						MainStudent student = new MainStudent();
						student.setVisible(true);
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

package com.twh.learnNet.server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This program implements a simple server that listens to port 8189 and echoes
 * back all client input.
 * 
 * @version 1.20 2004-08-03
 * @author Cay Horstmann
 */
public class MyEchoServer {
	public static void main(String[] args) {
		try {
			// establish server socket
			ServerSocket s = new ServerSocket(8189);

			// wait for client connection
			Socket incoming = s.accept();
			InputStreamReader inSR = null;
			OutputStreamWriter outSW = null;
			try {
				// 读取数据
				inSR = new InputStreamReader(incoming.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(inSR);

				outSW = new OutputStreamWriter(incoming.getOutputStream(), "UTF-8");
				BufferedWriter bw = new BufferedWriter(outSW);

				String str = "";
				while (!s.isClosed() && (str = br.readLine()) != null) {
					str = str.trim();
					System.out.println("收到客户端消息：" + str);

					bw.write("已收到信息：" + str + " \r\n"); // 向客户端反馈消息，加上分行符以便客户端接收
					bw.flush();
				}

			} finally {
				inSR.close();
				outSW.close();
				incoming.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

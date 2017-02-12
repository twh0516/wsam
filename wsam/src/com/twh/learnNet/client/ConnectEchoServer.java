package com.twh.learnNet.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectEchoServer {
	public static void main(String args[]) {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress("127.0.0.1", 8189), 1000);
			InputStreamReader inSR = null;
			OutputStreamWriter outSW = null;
			try {
				// 读取数据
				inSR = new InputStreamReader(socket.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(inSR);

				outSW = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
				BufferedWriter bw = new BufferedWriter(outSW);
				bw.write("你好！"+"\r\n");
				bw.flush();

				String str = "";
				while ((str = br.readLine()) != null) {
					str = str.trim();
					System.out.println("收到服务端消息：" + str);
				}
			} finally {
				inSR.close();
				outSW.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

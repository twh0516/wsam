package com.twh.learnNet;

import java.nio.ByteBuffer;

/**
 * This program makes a socket connection to the atomic clock in Boulder,
 * Colorado, and prints the time that the server sends.
 * 
 * @version 1.20 2004-08-03
 * @author Cay Horstmann
 */
public class SocketUtil {
	public static void main(String[] args) {
		ByteBuffer b = ByteBuffer.allocate(1024);
		ByteBuffer buf = buildHeader(b,100, 20);
		System.out.println(parseBodyLen(buf));
	}

	public static long parseBodyLen(ByteBuffer buf) {
		byte[] header = buf.array();
		long x = 0;
		byte n = 0;
		for (int i = 0; i < 4; i++) {
			x += (header[i] & 0xff) << n;
			n += 8;
		}
		return x;
	}
	
	public static ByteBuffer buildHeader(ByteBuffer buff,int jsonLen,int fileLen) {
		// 制作包头
		byte[] buf = buff.array();
		int size = jsonLen;
		

		// 第1~4个字节，为json包长度，低位在前，高位在后；不包括长度本身的4个字节
		buf[3] = (byte) (size >>> 24 & 0xFF);
		buf[2] = (byte) (size >>> 16 & 0xFF);
		buf[1] = (byte) (size >>> 8 & 0xFF);
		buf[0] = (byte) (size >>> 0 & 0xFF);
		
		//第21~24字节为文件长度
		buf[23] = (byte) (fileLen >>> 24 & 0xFF);
		buf[22] = (byte) (fileLen >>> 16 & 0xFF);
		buf[21] = (byte) (fileLen >>> 8 & 0xFF);
		buf[20] = (byte) (fileLen >>> 0 & 0xFF);
		

		// 第5个字节为操作/命令的编码；
//		buf[4] = (byte) cmdType;

		// 第6、7、8、9个字节，是客户端每次上传时数据包的标识，服务器返回时，前20个字节原样返回；

		// 第10个字节，16进制数据，客户端平台类型，定义如下：
		// 0x02-----android新版
		buf[9] = (byte) 0x02;
		buff = ByteBuffer.wrap(buf);
		return buff;
	}
}

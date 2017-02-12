package com.twh.learnNet.server;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class NonBlockingServer {
	public Selector sel = null;
	public ServerSocketChannel server = null;
	public SocketChannel socket = null;
	public int port = 8080;
	String result = null;

	public NonBlockingServer() {
		System.out.println("Inside default ctor");
	}

	public NonBlockingServer(int port) {
		System.out.println("Inside the other ctor");
		port = port;
	}

	public void initializeOperations() throws IOException, UnknownHostException {
		System.out.println("Inside initialization");
		sel = Selector.open();
		server = ServerSocketChannel.open();
		server.configureBlocking(false);
		InetAddress ia = InetAddress.getLocalHost();
		InetSocketAddress isa = new InetSocketAddress(ia, port);
		server.socket().bind(isa);
	}

	public void startServer() throws IOException {
		System.out.println("Inside startserver");
		initializeOperations();
		System.out.println("Abt to block on select()");
		SelectionKey acceptKey = server.register(sel, SelectionKey.OP_ACCEPT);
		while (acceptKey.selector().select() > 0) {

			Set readyKeys = sel.selectedKeys();
			Iterator it = readyKeys.iterator();

			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();
				it.remove();

				if (key.isAcceptable()) {
					System.out.println("Key is Acceptable");
					ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
					socket = (SocketChannel) ssc.accept();
					socket.configureBlocking(false);
					SelectionKey another = socket.register(sel, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
				}
				if (key.isValid() && key.isReadable()) {
					// System.out.println("Key is readable");
					// String ret = readMessage(key);
					String ret = readJsonData(key);
					//Localport为服务端的端口号
					SocketChannel socket = (SocketChannel) key.channel();
					System.out.println("localPort:" + socket.socket().getLocalPort()
							+"port" + socket.socket().getPort());
					// if (ret.length() > 0) {
					// writeMessage(socket,ret);
					// }
				}
				if (key.isValid() && key.isWritable()) {
					// System.out.println("THe key is writable");
					// String ret = readMessage(key);
					String ret = readJsonData(key);
					socket = (SocketChannel) key.channel();
					// if (result.length() > 0) {
					// writeMessage(socket,ret);
					// }
				}
			}
		}
	}

	public void writeMessage(SocketChannel socket, String ret) {
		System.out.println("Inside the loop");

		if (ret.equals("quit") || ret.equals("shutdown")) {
			return;
		}
		File file = new File(ret);
		try {
			RandomAccessFile rdm = new RandomAccessFile(file, "r");
			FileChannel fc = rdm.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			fc.read(buffer);
			buffer.flip();

			Charset set = Charset.forName("us-ascii");
			CharsetDecoder dec = set.newDecoder();
			CharBuffer charBuf = dec.decode(buffer);
			System.out.println(charBuf.toString());
			buffer = ByteBuffer.wrap((charBuf.toString()).getBytes());
			int nBytes = socket.write(buffer);
			System.out.println("nBytes = " + nBytes);
			result = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String readMessage(SelectionKey key) {
		int nBytes = 0;
		socket = (SocketChannel) key.channel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			nBytes = socket.read(buf);
			buf.flip();
			Charset charset = Charset.forName("us-ascii");
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(buf);
			result = charBuffer.toString();
			if (result.equals("quit")) {
				key.cancel();
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String readJsonData(SelectionKey key) {
		long nBytes = 0;
		socket = (SocketChannel) key.channel();
		String json = null;
		int bufSize = 1024;
		ByteBuffer buf = ByteBuffer.allocate(bufSize);
		List<byte[]> list = new ArrayList<byte[]>();
		try {
			while (socket.read(buf) > 0) {
				buf.flip();
				list.add(buf.array().clone());
				buf.clear();
			}
			System.out.println("list长度：" + list.size());
			int size = list.size() * bufSize;
//			byte[] total = new byte[size];
			ByteBuffer bf = ByteBuffer.allocate(size);
			for(byte[] barr : list) {
				for(byte b : barr) {
					bf.put(b);
				}
			}
			json = new String(bf.array(), "utf-8");
			System.out.println(json);
			result = json;
			JSONObject jsonObject = JSON.parseObject(json.trim());
			if(jsonObject.containsKey(com.twh.wsam.data.entity.CmdType.CMD) && jsonObject.getString(com.twh.wsam.data.entity.CmdType.CMD).equals("quit")) {
				key.cancel();
			}
//			Student student = JSON.parseObject(json.trim(),Student.class);
//			System.out.println(student);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static void main(String args[]) {
		NonBlockingServer nb = new NonBlockingServer();
		try {
			nb.startServer();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
}

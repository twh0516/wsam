package com.twh.wsam;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 * @author twh
 *
 */
public class DownloadClient implements Runnable {
	private SocketChannel socketChannel = null;
	private InetSocketAddress isa = null;
	private String json;
	private int port;
	private String serverIp;
	private Selector selector;
	private Callback callback;
	private boolean stop = false;
	FileOutputStream fout;
	FileChannel fcout;
	ByteBuffer buffer = ByteBuffer.allocateDirect(8 * 1024);

	public DownloadClient(String serverIp, int port, String json, Callback callback) throws FileNotFoundException {
		this.callback = callback;
		this.serverIp = serverIp;
		this.port = port;
		this.json = json;
		fout = new FileOutputStream("g:/out.mp4");
		fcout = fout.getChannel();
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void doConnect() throws IOException {
		isa = new InetSocketAddress(serverIp, port);
		boolean isFinish = socketChannel.connect(isa);
		// socketChannel.configureBlocking(false);
		if (isFinish) {
			socketChannel.register(selector, SelectionKey.OP_READ);
			doWrite();
		} else {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	public void close() throws UnsupportedEncodingException, IOException {
		socketChannel.write(getQuitBuffer());
		socketChannel.close();
	}

	private ByteBuffer getQuitBuffer() throws UnsupportedEncodingException {
		String quit = "{\"cmd\":\"quit\"}";
		byte[] b = quit.getBytes("utf-8");
		ByteBuffer buf = ByteBuffer.wrap(b);
		return buf;
	}

	private int doWrite() throws IOException {
		ByteBuffer bytebuf = ByteBuffer.allocate(1024);
		byte[] b = null;
		try {
			b = json.getBytes("utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		bytebuf = ByteBuffer.wrap(b);
		int nBytes = 0;
		nBytes = socketChannel.write(bytebuf);
		return nBytes;
	}

	public interface Callback {
		void notify(String json);
	}

	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!stop) {
			try {
				int num = selector.select();
				Set readyKeys = selector.selectedKeys();
				Iterator it = readyKeys.iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
					it.remove();
					try {
						download(key);
					} catch (Exception e) {
						e.printStackTrace();
						if (key != null) {
							key.cancel();
							if (key.channel() != null)
								key.channel().close();
						}

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		if (selector != null)
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	boolean haseFile = false;
	private void download(SelectionKey key) throws IOException {
		if (key.isValid()) {
			SocketChannel socket = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (socket.finishConnect()) {
					socket.register(selector, SelectionKey.OP_READ);
					doWrite();
				}
			}
			if (key.isReadable()) {
				try {
					buffer.clear();
					int read = 0;
					while ((read = socket.read(buffer)) > 0) {
						buffer.flip();
						int coutn = fcout.write(buffer);
						haseFile = true;
						buffer.clear();
					}
					if (read == -1) {
						key.cancel();
						stop = true;
//						fout.flush();
						fout.close();
						fcout.close();
						if(haseFile) {
							callback.notify("下载成功！");
						}else {
							callback.notify("下载失败！");
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					if (key != null) {
						key.cancel();
						if (key.channel() != null)
							key.channel().close();
					}
//					fout.flush();
					fout.close();
					fcout.close();
				}
			}
		}
	}

	private String readJsonData(SelectionKey key) {
		SocketChannel socket = (SocketChannel) key.channel();
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
			ByteBuffer bf = ByteBuffer.allocate(size);
			for (byte[] barr : list) {
				for (byte b : barr) {
					bf.put(b);
				}
			}
			json = new String(bf.array(), "utf-8");
			// System.out.println(json);
			// Student student = JSON.parseObject(json.trim(),Student.class);
			// System.out.println(student);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}

package com.twh.wsam.netClient;

import com.twh.util.Util;
import com.twh.wsam.ClientContract.ClientBasePresenter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author twh
 *
 */
public class TextNetClient implements Runnable {
	private SocketChannel socketChannel = null;
	private InetSocketAddress isa = null;
	private String json;
	private int port;
	private String serverIp;
	private Selector selector;
	private boolean stop = false;
	private ClientBasePresenter presenter;

	public TextNetClient(String serverIp, int port, String json, ClientBasePresenter presenter) {
		this.serverIp = serverIp;
		this.port = port;
		this.json = json;
		this.presenter = presenter;
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
		if (isFinish) {
			socketChannel.register(selector, SelectionKey.OP_READ);
			doWrite();
		} else {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	private void close() throws UnsupportedEncodingException, IOException {
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

	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			presenter.sendNetMessage(Util.getExceptionStackTrace(e));
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
						receiveMessage(key);
					} catch (Exception e) {
						presenter.sendNetMessage(Util.getExceptionStackTrace(e));
						closeConnect(key);

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (socketChannel != null) {
			try {
				socketChannel.socket().close();
				socketChannel.close();
			} catch (Exception e) {
			}
		}

	}

	private void receiveMessage(SelectionKey key) throws IOException {
		if (key.isValid()) {
			SocketChannel socket = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (socket.finishConnect()) {
					socket.register(selector, SelectionKey.OP_READ);
					doWrite();
				}
			}
			if (key.isReadable()) {
				String retJson = readJsonData(key);
				if (retJson != null && !retJson.equals("")) {
					presenter.onsubmitResult(retJson);
				} else {
					presenter.sendNetMessage("服务器已停止,请联系管理员!");
				}
				closeConnect(key);
			}
		}
	}

	private void closeConnect(SelectionKey key) throws IOException {
		if (key != null) {
			if (key.channel() != null){
				SocketChannel channel = (SocketChannel) key.channel();
				channel.socket().close();
				channel.close();
			}
			key.cancel();
		}
		stop = true;
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
			int size = list.size() * bufSize;
			ByteBuffer bf = ByteBuffer.allocate(size);
			for (byte[] barr : list) {
				for (byte b : barr) {
					bf.put(b);
				}
			}
			json = new String(bf.array(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(json != null)
			json = json.trim();
		return json;
	}
}

package com.twh.wsam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
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
import java.util.concurrent.TimeUnit;

import com.twh.util.Util;

/**
 * @author twh
 *
 */
public class SocketClient {
	private SocketChannel jsonClient = null;
	private SocketChannel downloadClient = null;
	private InetSocketAddress isa = null;
	private RecvThread rt = null;
	private int jsonPort, downloadPort;
	private String serverIp;
	private Selector selector;
	private final static String lock = "com.twh.wasm.Socketlient";
	private List<Callback> observers = new ArrayList<Callback>();
	public void registerObserver(Callback callback) {
		observers.add(callback);
	}
	public void removeObserver(Callback callback) {
		observers.remove(callback);
	}
	private SocketClient(String serverIp, int jsonPort, int downloadPort) throws IOException {
		this.serverIp = serverIp;
		this.jsonPort = jsonPort;
		this.downloadPort = downloadPort;
		selector = Selector.open();
	}

	private static SocketClient client;

	public static SocketClient getInstance(String serverIp, int jsonPort, int downloadPort) throws IOException {
		if (client == null) {
			synchronized (lock) {
				if (client == null) {
					client = new SocketClient(serverIp, jsonPort, downloadPort);
				}
			}
		}
		return client;
	}

	private void makeDownlaodConnection() {
		if (downloadClient != null && downloadClient.isOpen())
			return;
		try {
			downloadClient = SocketChannel.open();
			isa = new InetSocketAddress(serverIp, downloadPort);
			downloadClient.connect(isa);
			downloadClient.configureBlocking(false);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void makeJsonConnection() {
		if (jsonClient != null && jsonClient.isOpen())
			return;
		try {

			jsonClient = SocketChannel.open();
			isa = new InetSocketAddress(serverIp, jsonPort);
			jsonClient.connect(isa);
			jsonClient.configureBlocking(false);
			jsonClient.register(selector, SelectionKey.OP_READ);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeJsonClient() throws UnsupportedEncodingException, IOException {
		jsonClient.write(getQuitBuffer());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonClient.close();
	}

	private ByteBuffer getQuitBuffer() throws UnsupportedEncodingException {
		String quit = "{\"cmd\":\"quit\"}";
		byte[] b = quit.getBytes("utf-8");
		ByteBuffer buf = ByteBuffer.wrap(b);
		return buf;
	}

	public void closeDownloadClient() throws UnsupportedEncodingException, IOException {
		if (downloadClient == null || !downloadClient.isOpen())
			return;
		downloadClient.write(getQuitBuffer());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		downloadClient.close();
	}

	public int sendJsonData(String json) {
		makeJsonConnection();
		ByteBuffer bytebuf = ByteBuffer.allocate(1024);
		byte[] b = null;
		try {
			b = json.getBytes("utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		bytebuf = ByteBuffer.wrap(b);
		int nBytes = 0;
		try {
			nBytes = jsonClient.write(bytebuf);
			if (selector.select() > 0) {
				Set readyKeys = selector.selectedKeys();
				Iterator it = readyKeys.iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
					it.remove();
					if (key.isValid() && key.isReadable()) {
						SocketChannel socket = (SocketChannel) key.channel();
						if (socket.socket().getPort() == jsonPort) {
							String retJson = Util.readJsonData(key);
							for(Callback callback : observers) {
								callback.notify(retJson);
							}
						} else if (socket.socket().getPort() == downloadPort) {
							
						}
						// if (ret.length() > 0) {
						// writeMessage(socket,ret);
						// }
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return nBytes;
	}

	private void receiveMessage() {
		rt = new RecvThread("Receive THread", jsonClient);
		rt.start();

	}

	public void interruptThread() {
		// rt.val = false;
	}

	public static void main(String args[]) {
		// SocketClient cl = new SocketClient();
		// cl.makeJsonConnection();
	}

	public class RecvThread extends Thread {
		public SocketChannel sc = null;
		public boolean val = true;

		public RecvThread(String str, SocketChannel client) {
			super(str);
			sc = client;
		}

		public void run() {

			System.out.println("Inside receivemsg");
			int nBytes = 0;
			ByteBuffer buf = ByteBuffer.allocate(2048);
			try {
				while (val) {
					while ((nBytes = sc.read(buf)) > 0) {
						buf.flip();
						Charset charset = Charset.forName("us-ascii");
						CharsetDecoder decoder = charset.newDecoder();
						CharBuffer charBuffer = decoder.decode(buf);
						String result = charBuffer.toString();
						System.out.println(result);
						buf.flip();

					}
				}

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
	}
	public interface Callback{
		void notify(String json);
	}
}

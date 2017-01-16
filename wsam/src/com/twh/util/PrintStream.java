package com.twh.util;

import java.lang.ref.SoftReference;

public class PrintStream extends Thread {
	java.io.InputStream __is = null;
	SoftReference<Callback> callback;
	public PrintStream(java.io.InputStream is, Callback callback) {
		if (callback != null)
			this.callback = new SoftReference<Callback>(callback);
		__is = is;
	}
	public void run() {
		try {
			while (this != null) {
				int _ch = __is.read();
				if (_ch != -1) {
					System.out.print((char) _ch);
					if (callback != null) {
						callback.get().updateState(false);
					}

				} else {
					if(callback != null){
						callback.get().updateState(true);
					}
						
					System.out.print("???????");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static interface Callback {
		String[] show = new String[]{".",". .",". . ."};
		void updateState(boolean isFinish);
	}
}
package com.twh.wsam.netClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	public static ExecutorService textPool;
	public static ExecutorService filePool;
	private static String lock = "com.twh.wsam.netClient.ThreadPool";

	public static ExecutorService getTextPool() {
		if (textPool == null) {
			synchronized (lock) {
				if (textPool == null) {
					textPool = Executors.newFixedThreadPool(2);
//					textPool = Executors.newSingleThreadExecutor();
				}
			}
		}
		return textPool;
	}

	public static ExecutorService getFilePool() {
		if (filePool == null) {
			synchronized (lock) {
				if (filePool == null)
					filePool = Executors.newSingleThreadExecutor();
			}
		}
		return filePool;
	}

	public static void shutdown() {
		if (textPool != null)
			textPool.shutdownNow();
		if (filePool != null) {
			filePool.shutdownNow();
		}
	}
}

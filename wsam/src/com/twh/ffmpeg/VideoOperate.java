package com.twh.ffmpeg;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twh.util.LoggerUtil;
import com.twh.util.PrintStream;
import com.twh.util.PrintStream.Callback;

public class VideoOperate {
	private VideoOperate() {
	};

	private static String lock = "videoLock";

	public static VideoOperate getInstance() {
		if (videoOperate == null) {
			synchronized (lock) {
				if (videoOperate == null) {
					videoOperate = new VideoOperate();
				}
			}
		}
		return videoOperate;
	}

	private static VideoOperate videoOperate;

	public synchronized void cutVideo(String videoBeginTime, String videoEndTime, String ffmpeg, String inputFile,
			String outPath, String outName, Callback callback) {
		List<String> command = new ArrayList<String>();
		File path = new File(outPath);
		if (!path.exists())
			;
		path.mkdirs();
		outName = outPath + outName;
		if (LoggerUtil.isDebug) {
			path = new File(outName);
			if (path.exists()) {
				path.delete();
			}
		}
		/**
		 * 每个参数都不需要带空格，有空格会失败
		 */
		command.add(ffmpeg);
		command.add("-i");
		command.add(inputFile);
		command.add("-ss");
		command.add(videoBeginTime);
		command.add("-to");
		command.add(videoEndTime);
		command.add("-copyinkf");// 应该是解决音视频不同步问题
		command.add("-acodec");// 音频copy
		command.add("copy");
		command.add("-vcodec");// 视频copy
		command.add("copy");
		command.add(outName);
		Process videoProcess = null;
//		long cutStartTime = System.currentTimeMillis();
		try {

			videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PrintStream(videoProcess.getInputStream(), callback).start();
//		{//????????
//			try {
//				videoProcess.waitFor();// ??????о??????????????????е???s
//				long endStopTime = System.currentTimeMillis();
//				long length = endStopTime - cutStartTime;
//
//				System.out.println("???????,?????" + length / 1000);
//				outinfo.setText("???????,?????" + length / 1000 + "??");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}

	/**
	 * 获取视频时长
	 * 
	 * @param viedo_path
	 * @param ffmpeg_path
	 * @return
	 */
	public synchronized int getVideoTime(String video_path, String ffmpeg_path) {
		List<String> commands = new java.util.ArrayList<String>();
		commands.add(ffmpeg_path);
		commands.add("-i");
		commands.add(video_path);
		Logger log = Logger.getLogger("video");
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			final Process p = builder.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
			Pattern pattern = Pattern.compile(regexDuration);
			Matcher m = pattern.matcher(sb.toString());
			if (m.find()) {
				int time = getTimelen(m.group(1));
				log.info(m.group(1));
				log.info(video_path + ",视频时长" + time + ", 开始时间" + m.group(2) + ",码率" + m.group(3) + "kb/s");
				return time;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// 时间格式:"00:00:10.68"
	private static int getTimelen(String timelen) {
		int min = 0;
		String strs[] = timelen.split(":");
		if (strs[0].compareTo("0") > 0) {
			min += Integer.valueOf(strs[0]) * 60 * 60;
		}
		if (strs[1].compareTo("0") > 0) {
			min += Integer.valueOf(strs[1]) * 60;
		}
		if (strs[2].compareTo("0") > 0) {
			min += Math.round(Float.valueOf(strs[2]));
		}
		return min;
	}
}

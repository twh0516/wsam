
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Logger;

import com.twh.ffmpeg.VideoOperate;
import com.twh.util.LoggerUtil;
import com.twh.util.WindowsDivers;
import com.twh.wsam.util.AppUtil;

public class Main {
	public static void main(String args[]) throws IOException {
		// Logger log = LoggerUtil.getLoger();
		// try{
		// cutVideo();
		// }catch (Exception e) {
		// log.info(getExceptionStackTrace(e));
		// }
		
		System.out.println(WindowsDivers.getMaxDriveString());
	}



	/**
	 * ???????????
	 * 
	 * @param ex
	 * @return
	 */
	public static String getExceptionStackTrace(Throwable ex) {
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		ex.printStackTrace(pw);
		pw.close();
		String error = writer.toString();
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	private static int getVideoTime() {
		int time = 0;
		String ffmpeg = AppUtil.getProjectRootPath() + "ffmpeg/ffmpeg";// ????
		String inputFile = "F:/movie/???????33.mp4";
		time = VideoOperate.getInstance().getVideoTime(inputFile, ffmpeg);
		return time;
	}

	private static void cutVideo() {
		String videoBeginTime = "00:20:00";
		String videoEndTime = "00:25:00";
		String inputFile = "F:/movie/???????33.mp4";
		String outPath = AppUtil.getProjectRootPath() + "output/";
		String outName = "?????.mp4";
		Logger log = LoggerUtil.getLoger();
		log.info(outPath + outName + "\n");
		VideoOperate.getInstance().cutVideo(videoBeginTime, videoEndTime, AppUtil.getFFmpeg(), inputFile, outPath,
				outName, null);
	}
}

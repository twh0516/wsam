
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
		
//		System.out.println(getVideoTimeLength("d:/test.avi"));
		cutVideo();
	}



	/**
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

	private static int getVideoTimeLength(String file) {
		int time = 0;
		String ffmpeg = AppUtil.getProjectRootPath() + "ffmpeg/ffmpeg";
		time = VideoOperate.getInstance().getVideoTimeLength(file, ffmpeg);
		return time;
	}

	private static void cutVideo() {
		String videoBeginTime = "00:00:01";
		String videoEndTime = "00:25:00";
		String inputFile = "d:/test2.avi";
		String outPath = AppUtil.getProjectRootPath() + "output/";
		String outName = "out.avi";
		Logger log = LoggerUtil.getLoger();
		log.info(outPath + outName + "\n");
		VideoOperate.getInstance().cutVideo(videoBeginTime, videoEndTime, AppUtil.getFFmpeg(), inputFile, outPath,
				outName, null);
	}
}

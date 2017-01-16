package com.twh.util;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
	public static boolean isDebug = true;
	public static Logger getLoger(){
		Logger log = Logger.getLogger("lavasoft");
        log.setLevel(Level.INFO);
        ConsoleHandler consoleHandler =new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        log.addHandler(consoleHandler);
        FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("d:/testlog%g.log");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        fileHandler.setLevel(Level.INFO);
        log.addHandler(fileHandler);
        return log;
}
}

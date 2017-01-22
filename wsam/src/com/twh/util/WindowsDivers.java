package com.twh.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * windows操作系统
 * @author twh
 *
 */
public class WindowsDivers {
	/**
	 * windows获取剩余空间最大的磁盘
	 * @return
	 */
	public static String getMaxDriveString(){
		Map<String,Long> drives = getHardDiskFreeSpace();
		Entry<String,Long> entry_max = getMaxFreeDrive(drives);
		if(entry_max != null)
			return entry_max.getKey().replace("\\", "/");
		return null;
		
	}
	
	/**
	 * 获取windows系统各分区的剩余空间
	 * @return
	 */
	private static Map<String,Long> getHardDiskFreeSpace(){
		File[] drives = File.listRoots();
		Map<String,Long> map_hd_free = new HashMap<String, Long>();
		for (File drive : drives) {
			map_hd_free.put(drive.getPath(), drive.getFreeSpace());
		}
		return map_hd_free;
	}
	
	/**
	 * 得到剩余空间最大分区的map
	 * @param map_hd_free
	 * @return entry
	 */
	private static Entry<String, Long> getMaxFreeDrive(Map<String,Long> map_hd_free){
		Iterator<Entry<String, Long>> entries = map_hd_free.entrySet().iterator();
		Entry<String, Long> entry_max = null;
		while(entries.hasNext()){
			Entry<String, Long> entry = entries.next();
			if(entry_max == null){
				entry_max = entry;
			}else {
				if(entry.getValue().longValue() > entry_max.getValue().longValue()) {
					entry_max = entry;
				}
			}
		}
		return entry_max;
		
	}
}

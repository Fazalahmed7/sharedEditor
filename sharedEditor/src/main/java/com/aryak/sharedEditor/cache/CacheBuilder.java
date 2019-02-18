package com.aryak.sharedEditor.cache;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aryak.sharedEditor.editor.TextEditor;
import com.aryak.sharedEditor.service.SharedEditorService;

/**
 * @author aryghosh
 *
 */
@Component
public class CacheBuilder {
	@Autowired
	SharedEditorService sharedEditorService;
	
	public static volatile Map<String, TextEditor> cache = new WeakHashMap<>();
	/**
	 * remove Least Recently Used files (unused for 5 minutes)
	 */
	@Scheduled(fixedRate=500)
	public static void removeLeastRecentlyUsed() {
			Iterator<Entry<String, TextEditor>> entryIt = cache.entrySet().iterator();
			while (entryIt.hasNext() && cache.size()>0) {
				Entry<String, TextEditor> c = entryIt.next();
				TextEditor te = c.getValue();
				long presentTime = new Date().getTime();
				long timelapse = presentTime - te.getUpdatedTime();
				if (timelapse > 300000) {
					synchronized(CacheBuilder.class) {
					 entryIt.remove();
					 new File(c.getKey()).delete();
					 
					}
				}
		}
	}
}

package com.aryak.sharedEditor.service;

import static com.aryak.sharedEditor.constant.Constants.OPEN;
import static com.aryak.sharedEditor.constant.Constants.SAVE;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aryak.sharedEditor.action.EditorAction;
import com.aryak.sharedEditor.cache.CacheBuilder;
import com.aryak.sharedEditor.editor.TextEditor;

/**
 * @author aryghosh
 *
 */
@Service
public class SharedEditorService {
	public static final Logger logger = LoggerFactory.getLogger(EditorAction.class);

	/**
	 * get existing Text editor if not create a new one
	 * @param name
	 * @return
	 */
	public TextEditor createTextEditor(String name) {
		TextEditor note = null;
		ActionEvent ae = null;
		if (CacheBuilder.cache.containsKey(name)) {
			logger.trace("present in Cache " + name);
			note = CacheBuilder.cache.get(name);
			ae = new ActionEvent(note, 1, OPEN);
			note.actionPerformed(ae);
			note.setSize(500, 500);
			note.setVisible(true);
		} else {
			note = new TextEditor(name);
			note.setSize(500, 500);
			note.setVisible(true);

			CacheBuilder.cache.put(name, note);

			ae = new ActionEvent(note, ActionEvent.ACTION_FIRST, SAVE);
			note.actionPerformed(ae);

			return note;
		}
		return note;

	}

	/**
	 * clears the cache and delete all server files
	 * 
	 */
	public void clearCache() {
		Iterator<Entry<String, TextEditor>> entryIt = CacheBuilder.cache.entrySet().iterator();
		while (entryIt.hasNext() && CacheBuilder.cache.size() > 0) {
			Entry<String, TextEditor> c = entryIt.next();
			TextEditor te = c.getValue();
			new File(te.getFileName()).delete();
		}
		CacheBuilder.cache.clear();
	}

	/**
	 * clearFile -> deletes the file from server and clear it from cache as well
	 * 
	 * @param name
	 */
	public void clearFile(String name) {
		TextEditor te = CacheBuilder.cache.get(name);
		if (null != te) {
			new File(te.getFileName()).delete();
		}
		CacheBuilder.cache.remove(name);

	}
}

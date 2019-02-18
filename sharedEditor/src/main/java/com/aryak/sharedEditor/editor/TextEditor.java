package com.aryak.sharedEditor.editor;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.scheduling.annotation.Scheduled;
import static com.aryak.sharedEditor.constant.Constants.*;
public class TextEditor extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	private long updatedTime;
	private String fileName;
	private String name;
	

	// jframe
	MenuBar mbar;
	Menu file, edit, format, font, font1, font2;
	MenuItem item1, item2, item3, item4;
	MenuItem item5, item6, item7, item8, item9, item10;
	
	JPanel mainpanel;
	TextArea text;

	Font f;
	String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	GregorianCalendar gcalendar;

	String command = " ";
	String str = " ";

	String str1 = " ", str2 = " ", str3 = " ";
	String str4 = " ";

	String str7 = " ", str8 = " ", str9 = " ";

	int len1;

	int i = 0;
	int pos1;
	int len;
	

	public TextEditor(String name) {

		super(name);
		setUpdatedTime(new Date().getTime());
		this.fileName = UUID.randomUUID().toString().toUpperCase();
		this.name = name;
		setJFrameProperties();
	}

	public void actionPerformed(ActionEvent ae) {

		command = (String) ae.getActionCommand();

		/*
		 * if (command.equals("New...")) { dispose(); Texteditor note1 = new
		 * Texteditor("Untitled-Notepad"); note1.setSize(500, 500);
		 * note1.setVisible(true); }
		 */

		if (command.equals(OPEN)) {
			try {

				text.setText(readFile());

			} catch (IOException e) {
			}

		}

		if (command.equals(SAVE)) {
			try {
				String txt = text.getText();
				len1 = txt.length();
				byte buf[] = txt.getBytes();
				setPath(fileName);
				File f1 = new File(fileName);
				FileOutputStream fobj1 = new FileOutputStream(f1);
				for (int k = 0; k < len1; k++) {
					fobj1.write(buf[k]);
				}
				fobj1.close();
				setUpdatedTime(new Date().getTime());

				this.setTitle(name.substring(0,name.indexOf(DELIMITER)));

			} catch (IOException e) {
			}
		}
		if (command.equals(EXIT)) {
			if (null != getPath()) {
				new File(getPath()).delete();
			}
			setUpdatedTime(new Date().getTime());
			System.exit(0);
		}
		if (command.equals(CUT)) {
			str = text.getSelectedText();
			i = text.getText().indexOf(str);
			text.replaceRange(" ", i, i + str.length());
		}

		if (command.equals(COPY)) {
			str = text.getSelectedText();
		}

		if (command.equals(PASTE)) {
			pos1 = text.getCaretPosition();
			text.insert(str, pos1);
		}
		if (command.equals(DELETE)) {
			String msg = text.getSelectedText();
			i = text.getText().indexOf(msg);
			text.replaceRange(" ", i, i + msg.length());
		}


	}

	private String readFile() throws IOException {
		String readData = "";
		File f = new File(getPath());
		FileInputStream fobj = new FileInputStream(f);
		len = (int) f.length();
		for (int j = 0; j < len; j++) {
			char str5 = (char) fobj.read();
			readData = readData + str5;

		}
		fobj.close();
		return readData;
	}

	public void setJFrameProperties() {
		mainpanel = new JPanel();
		mainpanel = (JPanel) getContentPane();
		mainpanel.setLayout(new FlowLayout());


		mbar = new MenuBar();
		setMenuBar(mbar);

		file = new Menu(FILE);
		edit = new Menu(EDIT);

		file.add(item2 = new MenuItem(OPEN));
		file.add(item3 = new MenuItem(SAVE));
		file.add(item4 = new MenuItem(EXIT));
		mbar.add(file);

		edit.add(item5 = new MenuItem(CUT));
		edit.add(item6 = new MenuItem(COPY));
		edit.add(item7 = new MenuItem(PASTE));
		edit.add(item8 = new MenuItem(DELETE));
		edit.add(item10 = new MenuItem(SELECT_ALL));
		mbar.add(edit);

		

		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		item8.addActionListener(this);
		item10.addActionListener(this);
		
		text = new TextArea(26, 60);
		mainpanel.add(text);

		f = new Font("Monotype Corsiva", Font.PLAIN, 15);
		text.setFont(f);
	}

	private void saveTextContent() {

		byte buf[] = text.getText().getBytes();
		File f1 = new File(getPath());
		FileOutputStream fobj1 = null;
		try {
			fobj1 = new FileOutputStream(f1);
			for (int k = 0; k < text.getText().length(); k++) {
				synchronized (this) {
					fobj1.write(buf[k]);	
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fobj1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path =  path;
	}


	

	@Scheduled(fixedRate=1000)
	public void keyReleased(KeyEvent ev) {
		saveTextContent();
		try {
			text.setText(readFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the updatedTime
	 */
	public long getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime
	 *            the updatedTime to set
	 */
	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.TabList;

public class FileManager {
	private final String FILE_EXT = ".ser";

	public void saveFile(TabList tabList) {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		String fileName = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			int showSaveDialog = chosenFile.showSaveDialog(null);
			if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath().toString() + FILE_EXT;
			}
			if (fileName != null) {
//				fileOut = new FileOutputStream(new File(fileName));
//				out = new ObjectOutputStream(fileOut);
//				out.writeObject(tabList);
////				out.writeObject(tabbedPane);
//				fileOut.flush();
//				JOptionPane.showMessageDialog(null, "File Saved!");
				ObjectOutput out1 = new ObjectOutputStream(new FileOutputStream(fileName));
				out1.writeObject(tabList);
//				out1.writeObject(pane);
				out1.close();

				// Serialize to a byte array
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				out1 = new ObjectOutputStream(bos);
				out1.writeObject(tabList);
//				out1.writeObject(pane);
				out1.close();
				out1.flush();
			}
		} catch (Exception i) {
			JOptionPane.showMessageDialog(null, "Error occured! Could Not Save your file");
			i.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void loadFile() {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		String fileName = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_EXT, FILE_EXT.replace(".", ""));
			chosenFile.setFileFilter(filter);
			int showOpenDialog = chosenFile.showOpenDialog(null);
			if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath();
				fileIn = new FileInputStream(fileName);
				in = new ObjectInputStream(fileIn);
//				List<Tab> tabList = (ArrayList<Tab>) in.readObject();
//				TabList.getInstance().setTabList(tabList);
//				for(Tab tab : tabList) {
//					tab.getWorkspace().repaint();
//				}
//				System.out.println(TabList.getInstance().getTab().getWorkspace());
//				TabList.tabListInstance = (TabList) in.readObject();
//				TabList.tabListInstance.getTab().getWorkspace().repaint();
//				System.out.println(TabList.getInstance().getTab().getWorkspace());
//				return (JTabbedPane) in.readObject();
//				ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
//				TabList tabList = TabList.getInstance();
//				out.writeObject(tabList);

				ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(new File(fileName)));
				TabList.tabListInstance = (TabList)in1.readObject();
				TabList.tabListInstance.getTab().getWorkspace().repaint();
				System.out.println(TabList.tabListInstance);
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Could not load the file. Please select only .ser files!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

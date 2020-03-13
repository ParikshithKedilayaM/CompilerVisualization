package controller;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Connections;
import model.OptionNames;
import model.Tab;
import model.TabList;
import view.CloseBracket;
import view.DoubleBar;
import view.GreaterThan;
import view.Icons;
import view.LessThan;
import view.OpenBracket;

public class FileManager {
	private final String FILE_EXT = ".ser";
	
	public void saveFile() {
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
				fileOut = new FileOutputStream(new File(fileName));
				out = new ObjectOutputStream(fileOut);
				List<Tab> tabList = TabList.getInstance().getTabList();
				out.writeInt(tabList.size());
				for (Tab tab: tabList) {
					out.writeObject(tab.getIconList());
					out.writeObject(tab.getConnectionList());
				}
				fileOut.flush();
				JOptionPane.showMessageDialog(null, "File Saved!");
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
	
	public JTabbedPane loadFile() {
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
				int numberOfTabs = in.readInt();
				// create workspaces
				for (int i=0;i<numberOfTabs;i++) {
					TabList tabList = TabList.getInstance();
					tabList.setCurrentTabIndex(i);
//					in.readObject();
					tabList.getTab().setIconList((ArrayList<Icons>) in.readObject());
					tabList.getTab().setConnectionList((List<Connections>) in.readObject());
//					for (Connections conn : tabList.getTab().getConnectionList()) {
//						IconFactory icf = new IconFactory();
//						Graphics graphics = tabList.getTab().getWorkspace().getGraphics();
//						Icons originIcon = conn.getOriginIcon();
//						originIcon = icf.drawIcon(originIcon.getLocation(), getClassAsString(originIcon), graphics);
//						tabList.getTab().addIcon(originIcon);
//						Icons destIcon = conn.getDestIcon();
//						destIcon = icf.drawIcon(destIcon.getLocation(), getClassAsString(destIcon), graphics);
//						tabList.getTab().addIcon(destIcon);
////						Connections conn1 = new Connections();
////						conn1.setOriginIcon(originIcon);
////						conn1.setDestIcon(destIcon);
////						conn1.setOriginPoint(originIcon.getLocation());
////						conn1.setDestPoint(destIcon.getLocation());
////						tabList.getTab().getConnectionList().add(conn1);
//					}
					tabList.getTab().getWorkspace().repaint();
				}
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
		return new JTabbedPane();
	}
	private String getClassAsString(Icons icon) {
		if (icon instanceof OpenBracket) {
			return OptionNames.OPENBRACKET;
		} else if (icon instanceof CloseBracket) {
			return OptionNames.CLOSEBRACKET;
		} else if (icon instanceof GreaterThan) {
			return OptionNames.GREATERTHAN;
		} else if (icon instanceof LessThan) {
			return OptionNames.LESSTHAN;
		}
		return "";
	}
}

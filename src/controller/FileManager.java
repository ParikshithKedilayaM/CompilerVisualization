package controller;

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
import view.GreaterThan;
import view.Icons;
import view.LessThan;
import view.OpenBracket;
import view.Workspace;

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
				for (Tab tab : tabList) {
					out.writeObject(tab.getWorkspace());
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

	public void loadFile(JTabbedPane jTabbedPane) {
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
				TabList tabList = TabList.getInstance();
				int currentTabIndex = tabList.getCurrentTabIndex();
				for (int i = 0; i < numberOfTabs; i++) {
					Workspace workspace = (Workspace) in.readObject();
					jTabbedPane.add("Tab " + (tabList.getSize() + 1), workspace);
					tabList.setCurrentTabIndex(tabList.getSize());
					tabList.addTab(workspace);
					tabList.getTab().setWorkspace(workspace);
					tabList.getTab().addObserver(new WorkspaceController());
					tabList.getTab().setIconList((ArrayList<Icons>) in.readObject());
					tabList.getTab().setConnectionList((List<Connections>) in.readObject());
				}
				tabList.setCurrentTabIndex(currentTabIndex);
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

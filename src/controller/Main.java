package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.TabList;
import view.Workspace;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private OptionsPane optionsPanel;
	private JTabbedPane tabbedPane;
	private List<Workspace> workspaceList = new ArrayList<Workspace>();
	private final String TITLE = "Team 1";
	private Dimension screenSize;

	public Main() {
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLayout(null);
		setTitle(TITLE);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createOptionsPanel() {
		optionsPanel = new OptionsPane();
		optionsPanel.setBounds(0, 0, screenSize.width / 4, screenSize.height);
		optionsPanel.setVisible(true);
		this.add(optionsPanel);
	}

	private void createTabs() {
		tabbedPane = new JTabbedPane();
		createWorkspace();
		tabbedPane.setVisible(true);
		tabbedPane.setBounds(screenSize.width / 4, 0, 3 * screenSize.width / 4, screenSize.height);
		this.add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				TabList.getInstance().setCurrentTabIndex(tabbedPane.getSelectedIndex());
				workspaceList.get(TabList.getInstance().getCurrentTabIndex()).repaint();
			}
		});

	}

	private void createWorkspace() {
		TabList tabList = TabList.getInstance();
		Workspace workspace = new Workspace();
		workspaceList.add(workspace);
		tabList.addTab(workspace);
		tabList.getRecentTab().addObserver(new WorkspaceController());
		tabbedPane.add("Tab " + tabList.getSize(), workspace);
	}

	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem saveButton = new JMenuItem("Save");
		FileManager fileManager = new FileManager();
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileManager.saveFile();
			}
		});
		JMenuItem loadButton = new JMenuItem("Load");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileManager.loadFile();
			}
		});
		menu.add(saveButton);
		menu.add(loadButton);
		JButton addWorkspaceButton = new JButton("Add WorkSpace");
		addWorkspaceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createWorkspace();
			}
		});
		addWorkspaceButton.setContentAreaFilled(false);
		JButton compileButton = new JButton("Compile");
		compileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(null, "Work in progress!");
				NodeCompiler.createAdjacencyList();
			}
		});
		compileButton.setContentAreaFilled(false);
		menuBar.add(menu);
		menuBar.add(javax.swing.Box.createHorizontalStrut(10));
		menuBar.add(addWorkspaceButton);
		menuBar.add(javax.swing.Box.createHorizontalStrut(10));
		menuBar.add(compileButton);
		this.setJMenuBar(menuBar);
	}

	public static void main(String[] args) {
		Main frame = new Main();
		frame.createOptionsPanel();
		frame.createTabs();
		frame.createMenu();
		frame.setVisible(true);
	}

}

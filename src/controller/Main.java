package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.TabList;
import view.Workspace;

/**
 * This class creates left panel, right panel and Menu button
 * 
 * @author Chandan
 * @version 4.0
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private OptionsPane optionsPanel;
	private JScrollPane scrollPane;
	private JTabbedPane tabbedPane;
	private final String TITLE = "Team 1";
	private Dimension screenSize;
	private FileManager fileManager;
	private WorkspaceController workspaceController;
	private NodeCompiler nodeCompiler;

	public Main() {
		nodeCompiler = new NodeCompiler();
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLayout(null);
		setTitle(TITLE);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createOptionsPanel() {

		optionsPanel = new OptionsPane();
		scrollPane = new JScrollPane(optionsPanel);
		optionsPanel.setBounds(0, 0, screenSize.width / 4, screenSize.height);
		optionsPanel.setVisible(true);
		scrollPane.setBounds(0, 0, screenSize.width / 4, screenSize.height);
		scrollPane.setVisible(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(optionsPanel);
		this.getContentPane().add(scrollPane);
	}

	/**
	 * Function to create multiple tabs in right panel.
	 */
	private void createTabs() {
		tabbedPane = new JTabbedPane();
		createWorkspace();
		tabbedPane.setVisible(true);
		//tabbedPane.setBounds(screenSize.width / 4, 0, 3 * screenSize.width / 4, screenSize.height);
		tabbedPane.setBounds(0, 0, screenSize.width , screenSize.height);
		this.add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				TabList.getInstance().setCurrentTabIndex(tabbedPane.getSelectedIndex());
				TabList.getInstance().getTab().getWorkspace().repaint();
			}
		});

	}

	/**
	 * Function to create workspace in each tab.
	 */
	private void createWorkspace() {
		TabList tabList = TabList.getInstance();
		Workspace workspace = new Workspace();
		tabList.addTab(workspace);
		workspaceController = new WorkspaceController();
		tabList.getRecentTab().addObserver(workspaceController);
		tabbedPane.add("Tab " + tabList.getSize(), workspace);
		System.out.println("-----"+tabbedPane.getTabCount());
		tabList.addFixedIcons();
	}

	/**
	 * Function to create menu bar with load,save, add workspace and compile
	 * buttons.
	 */
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem saveButton = new JMenuItem("Save");
		fileManager = new FileManager();
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
				fileManager.loadFile(tabbedPane);
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
		JMenu projectMenu = new JMenu("Project");
		JMenuItem compileButton = new JMenuItem("Compile");
		compileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nodeCompiler.createAdjacencyList();
			}
		});
		JMenuItem translateButton = new JMenuItem("Translate");
		translateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		projectMenu.add(compileButton);
		projectMenu.add(translateButton);
		menuBar.add(menu);
		menuBar.add(javax.swing.Box.createHorizontalStrut(10));
		menuBar.add(projectMenu);
		menuBar.add(javax.swing.Box.createHorizontalStrut(10));
		menuBar.add(addWorkspaceButton);
		this.setJMenuBar(menuBar);
	}

	public static void main(String[] args) {
		Main frame = new Main();
		// frame.createOptionsPanel();
		frame.createTabs();
		frame.createMenu();
		frame.setVisible(true);
	}

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private OptionsPane optionsPanel;
	private JTabbedPane tabbedPane;
	private List<Workspace> workspaceList = new ArrayList<Workspace>();
	private final String TITLE = "Team 1";
	private Dimension screenSize;
	private JMenuBar menuBar;

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
				BoxList.getInstance().setCurrentTabIndex(tabbedPane.getSelectedIndex());
			}
		});

	}

	private void createWorkspace() {
		BoxList boxList = BoxList.getInstance();
		Workspace workspace = new Workspace();
		workspaceList.add(workspace);
		boxList.addBox();
		tabbedPane.add("Tab " + boxList.getSize(), workspace);
	}

	private void createMenu() {
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem newWorkspace = new JMenuItem("Add WorkSpace");
		newWorkspace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createWorkspace();
			}
		});
		menu.add(newWorkspace);
		menuBar.add(menu);
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

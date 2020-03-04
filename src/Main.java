import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private OptionsPane optionsPanel;
	private Workspace workspace;
	private final String TITLE = "Team 1";
	private Dimension screenSize;
	
	public Main() {
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLayout(null);
		setTitle(TITLE);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void createOptionsPanel() {
		optionsPanel = new OptionsPane();
		optionsPanel.setBounds(0, 0, screenSize.width / 4, screenSize.height);
		optionsPanel.setVisible(true);
		this.add(optionsPanel);
	}
	private void createWorkSpace() {
		workspace = new Workspace();
		workspace.setBounds(screenSize.width / 4, 0, 3 * screenSize.width / 4, screenSize.height);
		workspace.setVisible(true);
		this.add(workspace);
	}
	private void createMenu() {
		//this.setJMenuBar(new Menu());
	}

	public static void main(String[] args) {
		Main frame = new Main();
		frame.createOptionsPanel();
		frame.createWorkSpace();
		frame.createMenu();
		frame.setVisible(true);
	}

}

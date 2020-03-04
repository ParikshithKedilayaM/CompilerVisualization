import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private JMenu menu;
	private JMenuItem load, save;
	private final String SAVE = "Save";
	private final String LOAD = "Load";
	private final String MENU = "Menu";

	public Menu() {
		menu = new JMenu(MENU);
		FileManager fileManager = new FileManager();
		
		//menu.add(save);
		//menu.add(load);
		this.add(menu);
	}
}

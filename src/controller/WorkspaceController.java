package controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;

import model.Connections;
import model.Tab;
import model.TabList;
import view.DoubleBar;
import view.Icons;

/**
 * This is the Controller class for workspace. This class observes changes in
 * Model class Tab and performs the required actions.
 * 
 * @author Parikshith Kedilaya Mallar
 * @version 1.0
 *
 */
public class WorkspaceController implements Observer {

	private IconFactory iconFactory;
	private final String OPENBRACKET = "OPEN_BRACKET";
	private final String CLOSEBRACKET = "CLOSE_BRACKET";
	private final String LESSTHAN = "LESS_THAN";
	private final String GREATERTHAN = "GREATER_THAN";
	private final String ATTHERATE = "AT_THE_RATE";
	private final String HYPHEN = "HYPHEN";
	private final String BARS = "BARS";
	private final int fixedFinalX = 10;
	private final int fixedFinalY = 10;
	private final int fixedFinaldiffY = 70;
	private Dimension screenSize;

	public WorkspaceController() {
		iconFactory = new IconFactory();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "Clicked") {
			newShape();
		} else if (arg == "Pressed") {
			setSelectedIcon();
		} else if (arg == "Dragged") {
			iconDragged();
		} else if (arg == "Drawline") {
			drawLine(true);
		} else if (arg == "DrawTempLine") {
			drawLine(false);
		} else if (arg == "DoubleClicked") {
			doubleClick();
		} else if (arg == "CreateFixedIcons") {
			createFixedIcons();
		} else if (arg == "Released") {
			mouseReleased();
		}

		repaint();
	}

	private void mouseReleased() {
		Tab tab = TabList.getInstance().getTab();
		Point point = tab.getPoint();
		if (point.getX() > screenSize.width / 10) {
			Icons icon = tab.getSelectedIcon();
			if (isFixed(tab, icon)) {
				addToIconList(icon);
			}
		}

	}

	private void addToIconList(Icons newIcon) {
		Tab tab = TabList.getInstance().getTab();
		if (searchIcons(tab) != null) {
			return;
		}
		if (!tab.isMoving()) {
			Icons drawnIcon = newIcon;
			if (drawnIcon != null) {
				// drawnIcon.drawShape(tab.getWorkspace().getGraphics());
				tab.addIcon(drawnIcon);
			}
		} else {
			tab.setFirstDotClicked(false);
			tab.setMoving(false);
			tab.getWorkspace().setDefaultCursor();
		}
	}

	private void createFixedIcons() {
		Tab tab = TabList.getInstance().getRecentTab();
		for (int i = 1; i <= 20; i++) {
			tab.addFixedIcon(iconFactory.getIconObject(new Point(fixedFinalX, fixedFinalY), OPENBRACKET));
			tab.addFixedIcon(
					iconFactory.getIconObject(new Point(fixedFinalX, fixedFinalY + 1 * fixedFinaldiffY), CLOSEBRACKET));
			tab.addFixedIcon(
					iconFactory.getIconObject(new Point(fixedFinalX, fixedFinalY + 2 * fixedFinaldiffY), LESSTHAN));
			tab.addFixedIcon(
					iconFactory.getIconObject(new Point(fixedFinalX, fixedFinalY + 3 * fixedFinaldiffY), GREATERTHAN));
			tab.addFixedIcon(
					iconFactory.getIconObject(new Point(fixedFinalX, fixedFinalY + 4 * fixedFinaldiffY), ATTHERATE));
			tab.addFixedIcon(
					iconFactory.getIconObject(new Point(fixedFinalX, fixedFinalY + 5 * fixedFinaldiffY), HYPHEN));
			tab.addFixedIcon(
					iconFactory.getIconObject(new Point(fixedFinalX, fixedFinalY + 6 * fixedFinaldiffY), BARS));
		}
	}

	/**
	 * This searches for the presence of the shape in the clicked point and sets the
	 * icon bounded by that region. This method is executed when the mouse is
	 * pressed on workspace.
	 */
	private void setSelectedIcon() {
		Tab tab = TabList.getInstance().getTab();
		Icons icon = searchIcons(tab);
		if (icon != null) {
			tab.setSelectedIcon(icon);
			tab.setSelectedOption(icon.getClass().getSimpleName());
		} else {
			icon = searchFixedIcons(tab);
			if (icon != null) {
				tab.setSelectedIcon(icon);
				tab.setSelectedOption(icon.getClass().getSimpleName());
			}
		}
	}

	/**
	 * This method calls the paintComponent() method of the workspace and redraws
	 * all the icons in the current workspace
	 */
	private void repaint() {
		TabList.getInstance().getTab().getWorkspace().repaint();
	}

	/**
	 * This method sets the new co-ordinates for the icons on drag. This method is
	 * called on mouse drag in the workspace.
	 */
	private void iconDragged() {
		Tab tab = TabList.getInstance().getTab();
		Icons selected = tab.getSelectedIcon();
		if (selected != null) {
			selected.setLocation(tab.getPoint());
		}
	}

	/**
	 * This method creates a new shape on click in the workspace. This method is
	 * called on mouse click in the workspace
	 */
	private void newShape() {
		Tab tab = TabList.getInstance().getTab();
		if (searchIcons(tab) != null || tab.getPoint().getX() < screenSize.width / 10) {
			return;
		}

		if (!tab.isMoving()) {
			Icons drawnIcon = iconFactory.getIconObject(tab.getPoint(), tab.getSelectedOption());
			if (drawnIcon != null) {
				drawnIcon.drawShape(tab.getWorkspace().getGraphics());
				tab.addIcon(drawnIcon);
			}
		} else {
			tab.setFirstDotClicked(false);
			tab.setMoving(false);
			tab.getWorkspace().setDefaultCursor();
		}

	}

	/**
	 * This method creates a line between the clicked dot and current position of
	 * mouse. This method calls setLine() that creates a line between two dots when
	 * the @param value is true.
	 * 
	 * @param isFinalLine - boolean indicating whether second dot is clicked or not
	 */
	private void drawLine(boolean isFinalLine) {
		Tab tab = TabList.getInstance().getTab();
		Connections connection = new Connections();
		connection.setOriginIcon(tab.getOriginIcon());
		connection.setDestIcon(tab.getDestIcon());
		connection.setOriginPoint(tab.getOriginPoint());
		connection.setDestPoint(tab.getDestPoint());
		if (isFinalLine) {
			setLine(tab, connection);
			tab.setMoving(false);
			tab.getWorkspace().setDefaultCursor();
		} else {
			tab.setMoving(true);
			tab.getWorkspace().setCrossHairCursor();
		}
	}

	/**
	 * This method draws a line between two dots. Called from drawLine() when
	 * isFinalLine param is true.
	 * 
	 * @param tab
	 * @param connection
	 */
	private void setLine(Tab tab, Connections connection) {
		if ((tab.isOriginInput() && !tab.isDestInput()) || (!tab.isOriginInput() && tab.isDestInput())) {
			tab.getConnectionList().add(connection);
			tab.setFirstDotClicked(false);
			if (!(tab.getOriginIcon() instanceof DoubleBar)) {
				tab.getOriginDot().setEnabled(false);
			}
			if (!(tab.getDestIcon() instanceof DoubleBar)) {
				tab.getDestDot().setEnabled(false);
			}
		}
	}

	/**
	 * This method takes input from user and stores it in instance of respective
	 * icon This method is called on mouse double click.
	 */
	private void doubleClick() {
		Tab tab = TabList.getInstance().getTab();
		Icons icon = searchIcons(tab);
		if (icon != null) {
			String description = tab.getWorkspace().getInputString(icon.getDescription());
			icon.setDescription(description);
		}
	}

	/**
	 * Searches the icons list and returns the object of the clicked icon, else
	 * return null
	 * 
	 * @param tab - Tab instance
	 * @return
	 */

	private boolean isFixed(Tab tab, Icons iconToSearch) {
		ListIterator<Icons> listIterator = tab.getFixedIconList().listIterator();
		while (listIterator.hasNext()) {
			Icons icon = listIterator.next();
			if (icon == iconToSearch) {
				return true;
			}
		}
		return false;

	}

	private Icons searchIcons(Tab tab) {
		ListIterator<Icons> listIterator = tab.getIconList().listIterator();
		while (listIterator.hasNext()) {
			Icons icon = listIterator.next();
			if (icon.containsIcon(tab.getPoint())) {
				return icon;
			}
		}
		return null;
	}

	private Icons searchFixedIcons(Tab tab) {
		ListIterator<Icons> listIterator = tab.getFixedIconList().listIterator();
		while (listIterator.hasNext()) {
			Icons icon = listIterator.next();
			if (icon.containsIcon(tab.getPoint())) {
				return icon;
			}
		}
		return null;
	}

}
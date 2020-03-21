package controller;

import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

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
		}
		repaint();
	}

	/**
	 * This searches for the presence of the shape in the clicked point and sets the
	 * icon bounded by that region. This method is executed when the mouse is
	 * pressed on workspace.
	 */
	private void setSelectedIcon() {
		Tab tab = TabList.getInstance().getTab();
		ListIterator<Icons> listIterator = tab.getIconList().listIterator();
		while (listIterator.hasNext()) {
			Icons icon = listIterator.next();
			if (icon.containsIcon(tab.getPoint())) {
				tab.setSelectedIcon(icon);
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
		iconFactory = new IconFactory();
		Tab tab = TabList.getInstance().getTab();
		if (!tab.isMoving()) {
			Icons drawnIcon = iconFactory.drawIcon(tab.getPoint(), tab.getSelectedOption(),
					tab.getWorkspace().getGraphics());
			if (drawnIcon != null) {
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
		ListIterator<Icons> listIterator = tab.getIconList().listIterator();
		while (listIterator.hasNext()) {
			Icons icon = listIterator.next();
			if (icon.containsIcon(tab.getPoint())) {
				String description = tab.getWorkspace().getInputString(icon.getDescription());
				icon.setDescription(description);
				break;
			}
		}
	}
}
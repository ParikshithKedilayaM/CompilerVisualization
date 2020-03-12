package Controller;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import Model.Connections;
import Model.Tab;
import Model.TabList;
import View.Icons;

public class WorkspaceController implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "Clicked") {
			newShape();
		} else if (arg == "Pressed") {
			setSelectedIcon();
		} else if (arg == "Dragged") {
			iconDragged();
		} else if (arg == "Drawline"){
			drawLine(true);
		} else if (arg == "DrawTempLine") {
			drawLine(false);
		}
		repaint();
	}

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

	private void repaint() {
		TabList.getInstance().getTab().getWorkspace().repaint();
	}

	private void iconDragged() {
		Tab tab = TabList.getInstance().getTab();
		Icons selected = tab.getSelectedIcon();
		if (selected != null) {
			selected.setLocation(tab.getPoint());
		}
	}

	private void newShape() {
		IconFactory iconFactory = new IconFactory();
		Tab tab = TabList.getInstance().getTab();
		if (!tab.isMoving()) {
			Icons drawnIcon = iconFactory.drawIcon(tab.getPoint(), tab.getSelectedOption(),
					tab.getWorkspace().getGraphics());
			if (drawnIcon != null) {
				tab.addIcon(drawnIcon);
			} 
		}
	}
	
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
			tab.getWorkspace().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else {
			tab.setMoving(true);
			tab.getWorkspace().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
	}
	private void setLine(Tab tab, Connections connection) {
		tab.getConnectionList().add(connection);
		tab.setFirstDotClicked(false);
		tab.getOriginDot().setEnabled(false);
		tab.getDestDot().setEnabled(false);
	}
}
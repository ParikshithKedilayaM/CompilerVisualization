package Controller;

import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import Model.Connections;
import Model.Tab;
import Model.TabList;
import View.Dot;
import View.DoubleBar;
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
		} else if (arg == "Drawline") {
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
			tab.getWorkspace().setDefaultCursor();
		} else {
			tab.setMoving(true);
			tab.getWorkspace().setCrossHairCursor();
		}
	}

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
}
package Controller;

import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

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
		} else {
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
		Icons drawnIcon = iconFactory.drawIcon(tab.getPoint(), tab.getSelectedOption(),
				tab.getWorkspace().getGraphics());
		if (drawnIcon != null) {
			tab.addIcon(drawnIcon);
		}
	}
}
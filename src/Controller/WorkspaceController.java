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
			setSelectedIcon();
		} else {
			iconDragged();
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
		Icons selected = TabList.getInstance().getTab().getSelectedIcon();
		selected.setLocation(TabList.getInstance().getTab().getPoint());
	}

}
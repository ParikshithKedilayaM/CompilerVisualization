package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import View.Icons;
import View.Workspace;

public class Tab extends Observable {
	private String selectedOption = "";
	private ArrayList<Icons> iconList;
	private Point point;
	private Icons selectedIcon;
	private Workspace workspace;
	private boolean isFirstDotClicked = false;
	

	public Tab(Workspace workspace) {
		iconList = new ArrayList<Icons>();
		this.workspace = workspace;
	}

	public String getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}

	public void addIcon(Icons icon) {
		iconList.add(icon);
	}

	public ArrayList<Icons> getIconList() {
		return iconList;
	}

	public void setIconList(ArrayList<Icons> iconList) {
		this.iconList = iconList;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Icons getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(Icons selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public void notifyMethod(String operation) {
		setChanged();
		notifyObservers(operation);
	}

}

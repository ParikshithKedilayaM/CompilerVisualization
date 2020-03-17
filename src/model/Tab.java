package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;

import view.Icons;
import view.Workspace;

public class Tab extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;
	private String selectedOption = "";
	private ArrayList<Icons> iconList;
	private Point point, originPoint, destPoint;
	private Icons selectedIcon;
	private Workspace workspace;
	private boolean isFirstDotClicked = false, isMoving = false, isDoubleClick = false;
	private Icons originIcon, destIcon;
	private JButton originDot, destDot;
	private List<Connections> connectionList;

	public Tab(Workspace workspace) {
		iconList = new ArrayList<Icons>();
		connectionList = new ArrayList<Connections>();
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

	public void setPoint(Point point, String operation) {
		this.point = point;
		this.notifyMethod(operation);
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

	public boolean isFirstDotClicked() {
		return isFirstDotClicked;
	}

	public void setFirstDotClicked(boolean isFirstDotClicked) {
		this.isFirstDotClicked = isFirstDotClicked;
	}

	public Icons getOriginIcon() {
		return originIcon;
	}

	public void setOriginIcon(Icons originIcon) {
		this.originIcon = originIcon;
	}

	public Icons getDestIcon() {
		return destIcon;
	}

	public void setDestIcon(Icons destIcon) {
		this.destIcon = destIcon;
	}

	public List<Connections> getConnectionList() {
		return connectionList;
	}

	public void setConnectionList(List<Connections> connectionList) {
		this.connectionList = connectionList;
	}

	public Point getOriginPoint() {
		return originPoint;
	}

	public void setOriginPoint(Point originPoint) {
		this.originPoint = originPoint;
	}

	public Point getDestPoint() {
		return destPoint;
	}

	public void setDestPoint(Point destPoint, String operation) {
		this.destPoint = destPoint;
		this.notifyMethod(operation);
	}

	public JButton getOriginDot() {
		return originDot;
	}

	public void setOriginDot(JButton originDot) {
		this.originDot = originDot;
	}

	public JButton getDestDot() {
		return destDot;
	}

	public void setDestDot(JButton destDot) {
		this.destDot = destDot;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public boolean isDoubleClick() {
		return isDoubleClick;
	}

	public void setDoubleClick(boolean isDoubleClick) {
		this.isDoubleClick = isDoubleClick;
	}

	public void notifyMethod(String operation) {
		setChanged();
		notifyObservers(operation);
	}
}

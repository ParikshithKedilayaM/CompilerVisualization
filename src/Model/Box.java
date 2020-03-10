package Model;

import java.util.ArrayList;

import View.Icons;

public class Box {
	private String selectedOption = "";
	private ArrayList<Icons> iconList;
	public Box() {
		iconList = new ArrayList<Icons>();
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
}


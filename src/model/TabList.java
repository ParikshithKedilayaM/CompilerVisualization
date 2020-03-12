package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import view.Workspace;

public class TabList implements Serializable{
	private static final long serialVersionUID = 1L;
	private int currentTabIndex;
	private List<Tab> tabList;
	private static TabList tabListInstance;

	private TabList() {
		currentTabIndex = 0;
		tabList = new ArrayList<Tab>();
	}

	public static TabList getInstance() {
		if (tabListInstance == null) {
			tabListInstance = new TabList();
		}
		return tabListInstance;
	}

	public Tab getTab() {
		return tabList.get(currentTabIndex);
	}
	
	public Tab getRecentTab() {
		return tabList.get(getSize() - 1);
	}

	public void addTab(Workspace workspace) {
		tabList.add(new Tab(workspace));
	}

	public int getCurrentTabIndex() {
		return currentTabIndex;
	}

	public void setCurrentTabIndex(int currentTabIndex) {
		this.currentTabIndex = currentTabIndex;
	}

	public int getSize() {
		return tabList.size();
	}

	public List<Tab> getTabList() {
		return tabList;
	}

	public void setTabList(List<Tab> tabList) {
		this.tabList = tabList;
	}
}

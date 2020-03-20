package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import view.Workspace;

/**
 * This is a singleton class that contains a list of all the tabs in the
 * workspace.
 * 
 * @author Raghavan
 * @version 1.0
 */
public class TabList implements Serializable {
	private static final long serialVersionUID = 1L;
	private int currentTabIndex;
	private List<Tab> tabList;
	private static TabList tabListInstance;

	private TabList() {
		currentTabIndex = 0;
		tabList = new ArrayList<Tab>();
	}

	/**
	 * Returns the singleton instance of the TabList class.
	 */
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

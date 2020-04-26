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

	public void addFixedIcons(){
		TabList.getInstance().getRecentTab().notifyFixedIcons("CreateFixedIcons");
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

	private final String OPENBRACKET = "OPEN_BRACKET";
	private final String CLOSEBRACKET = "CLOSE_BRACKET";
	private final String LESSTHAN = "LESS_THAN";	
	private final String GREATERTHAN = "GREATER_THAN";
	private final String ATTHERATE = "AT_THE_RATE";
	private final String HYPHEN = "HYPHEN";
	private final String BARS = "BARS";

	private final String OPENBRACKET_CLASSNAME = "OpenBracket";
	private final String CLOSEBRACKET_CLASSNAME = "CloseBracket";
	private final String LESSTHAN_CLASSNAME = "LessThan";	
	private final String GREATERTHAN_CLASSNAME = "GreaterThan";
	private final String ATTHERATE_CLASSNAME = "AtTheRate";
	private final String HYPHEN_CLASSNAME = "Hyphen";
	private final String BARS_CLASSNAME = "DoubleBars";

	public String getOpenBracket() {
		return OPENBRACKET;
	}

	public String getCloseBracket() {
		return CLOSEBRACKET;
	}

	public String getLessThan() {
		return LESSTHAN;
	}

	public String getGreaterThan() {
		return GREATERTHAN;
	}

	public String getAtTheRate() {
		return ATTHERATE;
	}

	public String getHyphen() {
		return HYPHEN;
	}

	public String getBars() {
		return BARS;
	}

	public String getOpenBracketClassName() {
		return OPENBRACKET_CLASSNAME;
	}

	public String getCloseBracketClassName() {
		return CLOSEBRACKET_CLASSNAME;
	}

	public String getLessThanClassName() {
		return LESSTHAN_CLASSNAME;
	}

	public String getGreaterThanClassName() {
		return GREATERTHAN_CLASSNAME;
	}

	public String getAtTheRateClassName() {
		return ATTHERATE_CLASSNAME;
	}

	public String getHyphenClassName() {
		return HYPHEN_CLASSNAME;
	}

	public String getBarsClassName() {
		return BARS_CLASSNAME;
	}
}

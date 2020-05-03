package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import model.Connections;
import model.Tab;
import model.TabList;
import view.AtTheRate;
import view.CloseBracket;
import view.DoubleBar;
import view.GreaterThan;
import view.Hyphen;
import view.Icons;
import view.LessThan;
import view.OpenBracket;
import view.Pound;

/**
 * This class does the compilation of the visual diagram, here DFS traversal is
 * performed and Stack is used to verify the connected diagrams.
 * 
 * @author Mayank Kataruka
 * @version 1.0
 * @author Parikshith Kedilaya Mallar
 * @version 1.1
 * 
 */

public class NodeCompiler {

	private String errorMessage = "";
	private String translateMessage = "";

	public void doTranslate() {
		Tab tab = TabList.getInstance().getTab();
		doCompile();
		if (errorMessage.length() != 0) {
			translate();
		} else {
			File file = new File("Translate.txt");
			String translatedText = "";
			try {
				String prepend = "digraph nodes{\n";
				String append = "}";
				translatedText = prepend + translateMessage + append;
				file.createNewFile();
				FileWriter writer = new FileWriter(file);
				writer.write(translatedText);
				writer.flush();
				writer.close();
				tab.getWorkspace().displayMessage("Translated Successfully. Saved in file Translate.txt");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				tab.getWorkspace().displayMessage("Could not translate the graph");
				e.printStackTrace();
			}
		}
	}

	public void doCompile() {
		Tab tab = TabList.getInstance().getTab();
		LinkedHashMap<Icons, LinkedList<Icons>> adjList = createAdjacencyList(tab);
		Stack<Icons> stack = new Stack<Icons>();
		Icons start = getStartIcon(adjList);
		if (start == null) {
			errorMessage = "No Connections on tab 1";
		} else
			traverse(adjList, start, stack, null, 0);

		for (Tab tabAll : TabList.getInstance().getTabList()) {
			for (Icons icon : tabAll.getIconList()) {
				icon.setFirstConnection(false);
			}
		}

		if (!stack.isEmpty() || start == null) {
			if (start == null) {
				errorMessage = "One or more connections missing";
			}
			tab.getWorkspace().displayMessage("Compilation Error. " + errorMessage);
		} else {
			tab.getWorkspace().displayMessage("Compilation Success");
		}
		// System.out.println("Translate Message = " + translateMessage);
		// System.out.println("Error message = " + errorMessage);
	}

	/**
	 * This method creates an adjacency list of all the nodes present in the graph.
	 * 
	 * @return
	 */
	public LinkedHashMap<Icons, LinkedList<Icons>> createAdjacencyList(Tab tab) {
		// Tab tab = TabList.getInstance().getTab();
		if (!checkIconCount()) {
			tab.getWorkspace().displayMessage("Compilation Error: " + errorMessage);
			return null;
		}
		LinkedHashMap<Icons, LinkedList<Icons>> adjList = new LinkedHashMap<Icons, LinkedList<Icons>>();
		List<Connections> connectionList = tab.getConnectionList();
		HashMap<Icons, Double> atTheRateLoc = new HashMap<Icons, Double>();
		for (Connections connection : connectionList) {
			Icons originIcon = connection.getOriginIcon();
			Icons destIcon = connection.getDestIcon();
			if (!adjList.containsKey(originIcon)) {
				LinkedList<Icons> list = new LinkedList<Icons>();
				list.add(destIcon);
				adjList.put(originIcon, list);
				if (originIcon instanceof AtTheRate)
					atTheRateLoc.put(originIcon, connection.getOriginPoint().getY());
			} else {
				LinkedList<Icons> list = adjList.get(originIcon);
				if (originIcon instanceof AtTheRate) {
					double getprevY = atTheRateLoc.get(originIcon);
					double getnewY = connection.getOriginPoint().getY();
					if (getnewY > getprevY)
						list.addFirst(destIcon);
					else
						list.add(destIcon);
				} else {
					list.add(destIcon);
				}
			}
		}

		for (Map.Entry<Icons, LinkedList<Icons>> map : adjList.entrySet()) {
			Icons key = map.getKey();
			if (key instanceof AtTheRate) {
				List<Icons> icon = map.getValue();
				if (icon.size() != 2) {
					tab.getWorkspace().displayMessage("Compilation Error: Loop missing @");
					return null;
				}
			}
		}

		return adjList;

		// Stack<Icons> stack = new Stack<Icons>();
		// Icons start = getStartIcon(adjList);
		// traverse(adjList, start, stack);
		// for (Icons icon : tab.getIconList()) {
		// icon.setFirstConnection(false);
		// }

		// if (!stack.isEmpty() || start == null) {
		// if (start == null) {
		// errorMessage = "One or more connections missing";
		// }
		// tab.getWorkspace().displayMessage("Compilation Error. " + errorMessage);
		// } else {
		// tab.getWorkspace().displayMessage("Compilation Success");
		// }
	}

	/**
	 * This method traverses through all the nodes and checks for multiple rules.
	 * This method follows Depth First Search algorithm
	 * 
	 * @param adjList - Adjacency list generated in createAdjacencyList()
	 * @param start   - starting node generated by getStartIcon()
	 * @param stack   - Stack of containing nodes.
	 */
	private void traverse(LinkedHashMap<Icons, LinkedList<Icons>> adjList, Icons start, Stack<Icons> stack,
			Icons previous, int tabId) {

		System.out.print(start + " ");
		if (previous != null) {
			String first = previous.toString().substring(previous.toString().indexOf(".") + 1).replace("@", "");
			String second = start.toString().substring(start.toString().indexOf(".") + 1).replace("@", "");
			translateMessage += first + "->" + second + "\n";
		}
		if (!stack.isEmpty() && start instanceof CloseBracket && stack.peek() instanceof OpenBracket) {
			stack.pop();
			return;
		}
		previous = start;
		if (start instanceof CloseBracket) {
			return;
		}

		List<Icons> list = adjList.get(start);
		if (list == null && !(start instanceof CloseBracket)) {
			errorMessage = "Connection from " + start.getClass().getSimpleName() + " missing on Tab " + (tabId + 1);
		}
		if (start instanceof LessThan && list.size() != 2) {
			errorMessage = "Connection from " + start.getClass().getSimpleName() + " missing on Tab " + (tabId + 1);
		}
		if (start instanceof AtTheRate && list.size() != 2) {
			errorMessage = "Connection from " + start.getClass().getSimpleName() + " missing on Tab " + (tabId + 1);
		}
		if (list == null)
			return;

		if (start instanceof Pound) {
			int tabIndex = ((Pound) start).getTabIndex();
			Tab tab = TabList.getInstance().getTab(tabIndex);
			LinkedHashMap<Icons, LinkedList<Icons>> adjListNext = createAdjacencyList(tab);
			Icons startNext = getStartIcon(adjListNext);
			if (startNext == null) {
				errorMessage = "No Connection present on tab " + (tabIndex + 1);
				return;
			}
			traverse(adjListNext, startNext, stack, previous, tabIndex);
		}

		if (start instanceof OpenBracket || start instanceof LessThan)
			stack.push(start);

		if (!stack.isEmpty() && start.isFirstConnection() && start instanceof GreaterThan
				&& stack.peek() instanceof LessThan) {
			stack.pop();
		}
		if (!stack.isEmpty() && start.isFirstConnection() && start instanceof AtTheRate
				&& stack.peek() instanceof AtTheRate) {
			stack.pop();
			return;
		}

		if (start.isFirstConnection() && start instanceof DoubleBar) {
			return;
		}

		if (start instanceof AtTheRate && !start.isFirstConnection()) {
			stack.push(start);
			start.setFirstConnection(true);
		}

		if (start instanceof DoubleBar && !start.isFirstConnection()) {
			start.setFirstConnection(true);
		}
		if (start instanceof GreaterThan && !start.isFirstConnection()) {
			start.setFirstConnection(true);
			return;
		}

		for (Icons icon : list) {

			traverse(adjList, icon, stack, previous, tabId);
		}
	}

	/**
	 * This method finds the start icon for the node traversal
	 * 
	 * @param adjList - Adjacency list generated in createAdjacencyList()
	 * @return
	 */
	private Icons getStartIcon(LinkedHashMap<Icons, LinkedList<Icons>> adjList) {
		Icons start = null;
		int countStartIcons = 0;
		for (Entry<Icons, LinkedList<Icons>> map : adjList.entrySet()) {
			Icons key = map.getKey();
			if (key instanceof OpenBracket) {
				start = key;
				countStartIcons++;
			}
		}
		if (countStartIcons >= 2) {
			errorMessage = "More than 1 open bracket or close bracket";
			return null;
		}
		return start;
	}

	/**
	 * This method checks if there are multiple open/close brackets.
	 * 
	 * @return
	 */
	private boolean checkIconCount() {
		int countOpenBracket = 0, countCloseBracket = 0;
		Tab tab = TabList.getInstance().getTab();
		if (tab.getIconList().size() == 0) {
			errorMessage = "Nothing to compile!";
			return false;
		}
		for (Icons icon : tab.getIconList()) {
			if (icon instanceof OpenBracket) {
				countOpenBracket++;
			} else if (icon instanceof CloseBracket) {
				countCloseBracket++;
			}
		}
		if (countCloseBracket > 1 || countOpenBracket > 1) {
			errorMessage = "More than one open or close brackets";
			return false;
		}
		return true;
	}

	public void translate() {
		Tab tab = TabList.getInstance().getTab();
		List<Icons> allNodes = tab.getIconList();
		Set<Icons> presentInAdj = new HashSet<Icons>();
		HashMap<Icons, String> map = new HashMap<Icons, String>();
		Set<String> graphNodeConnections = new HashSet<String>();
		LinkedHashMap<Icons, LinkedList<Icons>> adjList = new LinkedHashMap<Icons, LinkedList<Icons>>();
		List<Connections> connectionList = tab.getConnectionList();
		HashMap<Icons, Double> atTheRateLoc = new HashMap<Icons, Double>();
		for (Connections connection : connectionList) {
			Icons originIcon = connection.getOriginIcon();
			Icons destIcon = connection.getDestIcon();
			if (!adjList.containsKey(originIcon)) {
				LinkedList<Icons> list = new LinkedList<Icons>();
				list.add(destIcon);
				adjList.put(originIcon, list);
				if (originIcon instanceof AtTheRate)
					atTheRateLoc.put(originIcon, connection.getOriginPoint().getY());
			} else {
				LinkedList<Icons> list = adjList.get(originIcon);
				if (originIcon instanceof AtTheRate) {
					double getprevY = atTheRateLoc.get(originIcon);
					double getnewY = connection.getOriginPoint().getY();
					if (getnewY > getprevY)
						list.addFirst(destIcon);
					else
						list.add(destIcon);
				} else {
					list.add(destIcon);
				}
			}
		}

		int openBracketCount = 1, atTheRateCount = 1, closeBracketCount = 1, doubleBarCount = 1, greaterThanCount = 1,
				hyphenCount = 1, lessThanCount = 1;

		for (Icons icons : allNodes) {
			if (map.containsKey(icons)) {
				continue;
			} else {
				if (icons instanceof LessThan) {
					map.put(icons,
							icons.toString().substring(icons.toString().indexOf('.') + 1, icons.toString().indexOf('@'))
									+ lessThanCount++);
				} else if (icons instanceof GreaterThan) {
					map.put(icons,
							icons.toString().substring(icons.toString().indexOf('.') + 1, icons.toString().indexOf('@'))
									+ greaterThanCount++);
				} else if (icons instanceof CloseBracket) {
					map.put(icons,
							icons.toString().substring(icons.toString().indexOf('.') + 1, icons.toString().indexOf('@'))
									+ closeBracketCount++);
				} else if (icons instanceof OpenBracket) {
					map.put(icons,
							icons.toString().substring(icons.toString().indexOf('.') + 1, icons.toString().indexOf('@'))
									+ openBracketCount++);
				} else if (icons instanceof AtTheRate) {
					map.put(icons,
							icons.toString().substring(icons.toString().indexOf('.') + 1, icons.toString().indexOf('@'))
									+ atTheRateCount++);
				} else if (icons instanceof Hyphen) {
					map.put(icons,
							icons.toString().substring(icons.toString().indexOf('.') + 1, icons.toString().indexOf('@'))
									+ hyphenCount++);
				} else if (icons instanceof DoubleBar) {
					map.put(icons,
							icons.toString().substring(icons.toString().indexOf('.') + 1, icons.toString().indexOf('@'))
									+ doubleBarCount++);
				}

			}

		}

		for (Icons icon : adjList.keySet()) {
			presentInAdj.add(icon);
			for (Icons icons : adjList.get(icon)) {
				graphNodeConnections.add(map.get(icon) + "->" + map.get(icons) + ";");
				presentInAdj.add(icons);
			}

		}

		for (Icons icon : allNodes) {
			if (!presentInAdj.contains(icon)) {
				graphNodeConnections.add(map.get(icon));
			}
		}
		String translatedText = "";

		for (String s : graphNodeConnections) {
			translatedText += s + '\n';
		}

		File file = new File("Translate.txt");
		try {
			String prepend = "digraph nodes{\n";
			String append = "}";
			translatedText = prepend + translatedText + append;
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(translatedText);
			writer.flush();
			writer.close();
			tab.getWorkspace().displayMessage("Translated Successfully. Saved in file Translate.txt");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			tab.getWorkspace().displayMessage("Could not translate the graph");
			e.printStackTrace();
		}

	}
}
package controller;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import javax.swing.JOptionPane;

import model.Connections;
import model.Tab;
import model.TabList;
import view.AtTheRate;
import view.CloseBracket;
import view.DoubleBar;
import view.GreaterThan;
import view.Icons;
import view.LessThan;
import view.OpenBracket;

/**
 * This class does the compilation of the visual diagram, here DFS traversal is
 * performed and Stack is used to verify the connected diagrams.
 * 
 * @author Mayank Kataruka
 * @version 1.0
 */

public class NodeCompiler {

	private String errorMessage = "";

	public void createAdjacencyList() {

		LinkedHashMap<Icons, LinkedList<Icons>> adjList = new LinkedHashMap<Icons, LinkedList<Icons>>();
		Tab tab = TabList.getInstance().getTab();
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
					JOptionPane.showMessageDialog(null, "Compiler Error");
					return;
				}
			}
		}

		Stack<Icons> stack = new Stack<Icons>();
		Icons start = getStartIcon(adjList);

		traverse(adjList, start, stack);
		for (Icons icon : tab.getIconList()) {
			icon.setFirstConnection(false);
		}

		if (!stack.isEmpty() || start == null)
			JOptionPane.showMessageDialog(null, "Compiler Error. " + errorMessage);
		else
			JOptionPane.showMessageDialog(null, "Compilation Success");
	}

	public void traverse(LinkedHashMap<Icons, LinkedList<Icons>> adjList, Icons start, Stack<Icons> stack) {

		if (!stack.isEmpty() && start instanceof CloseBracket && stack.peek() instanceof OpenBracket) {
			stack.pop();
			return;
		}
		if (start instanceof CloseBracket) {
			return;
		}

		List<Icons> list = adjList.get(start);

		if (list == null)
			return;

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

			traverse(adjList, icon, stack);
		}
	}

	public Icons getStartIcon(LinkedHashMap<Icons, LinkedList<Icons>> adjList) {
		Icons start = null;
		int countStartIcons = 0, countCloseIcons = 0;
		for (Entry<Icons, LinkedList<Icons>> map : adjList.entrySet()) {
			Icons key = map.getKey();
			if (key instanceof OpenBracket) {
				start = key;
				countStartIcons++;
			}

			if (key instanceof CloseBracket) {
				countCloseIcons++;
			}
		}
		if (countStartIcons >= 2 || countCloseIcons >= 2) {
			errorMessage = "More than 1 open bracket or close bracket";
			return null;
		}
		return start;
	}

}

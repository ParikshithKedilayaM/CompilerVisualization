package controller;

import java.util.ArrayList;
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

public class NodeCompiler {

	public void createAdjacencyList() {

		LinkedHashMap<Icons, LinkedList<Icons>> adjList = new LinkedHashMap<Icons, LinkedList<Icons>>();
		Tab tab = TabList.getInstance().getTab();
		ArrayList<Icons> iconList = tab.getIconList();
		List<Connections> connectionList = tab.getConnectionList();
		for (Icons icon : iconList) {

		}
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
		System.out.println(adjList);

		for (Map.Entry<Icons, LinkedList<Icons>> map : adjList.entrySet()) {
			Icons key = map.getKey();
			if (key instanceof AtTheRate) {
				List<Icons> icon = map.getValue();
				if (icon.size() != 2) {
					JOptionPane.showMessageDialog(null, "Compiler Error");
					return;
				} else {
					Icons icon1 = icon.get(0);
					Icons icon2 = icon.get(1);

				}
			}
		}

		for (Connections connection : connectionList) {
			System.out.print(connection.getOriginIcon() + "->" + connection.getOriginPoint());
			System.out.println(connection.getDestIcon() + "-" + connection.getDestPoint());
		}

		Stack<Icons> stack = new Stack<Icons>();
		Icons start = getStartIcon(adjList);
		traverse(adjList, start, stack);
		for (Icons icon: tab.getIconList()) {
			icon.setFirstConnection(false);
		}
		if (!stack.isEmpty())
			JOptionPane.showMessageDialog(null, "Compiler Error");
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
		
		if(start.isFirstConnection() && start instanceof DoubleBar) {
			return;
		}

		if (start instanceof AtTheRate && !start.isFirstConnection()) {
			stack.push(start);
			start.setFirstConnection(true);
		}
		
		if (start instanceof DoubleBar && !start.isFirstConnection()) {
			start.setFirstConnection(true);
		}

		for (Icons icon : list) {
			if (icon instanceof GreaterThan && !icon.isFirstConnection()) {
				icon.setFirstConnection(true);
				return;
			}
			traverse(adjList, icon, stack);
		}
	}

	public static Icons getStartIcon(LinkedHashMap<Icons, LinkedList<Icons>> adjList) {
		Icons start = null;
		int count = 0;
		for (Entry<Icons, LinkedList<Icons>> map : adjList.entrySet()) {
			Icons key = map.getKey();
			if (key instanceof OpenBracket) {
				start = key;
				count++;
			}
		}
		if (count >= 2)
			System.err.println("More than one open bracket");
		return start;
	}

}

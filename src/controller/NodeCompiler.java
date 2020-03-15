package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.JOptionPane;

import model.Connections;
import model.Tab;
import model.TabList;
import view.AtTheRate;
import view.CloseBracket;
import view.GreaterThan;
import view.Icons;
import view.LessThan;
import view.OpenBracket;

public class NodeCompiler {

	public void createAdjacencyList() {

		LinkedHashMap<Icons, List<Icons>> adjList = new LinkedHashMap<Icons, List<Icons>>();
		Tab tab = TabList.getInstance().getTab();
		ArrayList<Icons> iconList = tab.getIconList();
		List<Connections> connectionList = tab.getConnectionList();
		for (Icons icon : iconList) {

		}
		for (Connections connection : connectionList) {
			Icons originIcon = connection.getOriginIcon();
			Icons destIcon = connection.getDestIcon();
			if (!adjList.containsKey(originIcon)) {
				List<Icons> list = new ArrayList<Icons>();
				list.add(destIcon);
				adjList.put(originIcon, list);
			} else {
				List<Icons> list = adjList.get(originIcon);
				list.add(destIcon);
			}

		}
		for (Map.Entry<Icons, List<Icons>> map : adjList.entrySet()) {
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
		Stack<Icons> stack = new Stack<Icons>();
		System.out.println(adjList);
		Icons start = getStartIcon(adjList);
		traverse(adjList, start, stack);
		System.out.println(stack);
		if (!stack.isEmpty())
			JOptionPane.showMessageDialog(null, "Compiler Error");
		else
			JOptionPane.showMessageDialog(null, "Compilation Success");
	}

	public void traverse(LinkedHashMap<Icons, List<Icons>> adjList, Icons start, Stack<Icons> stack) {

		if (!stack.isEmpty() && start instanceof CloseBracket && stack.peek() instanceof OpenBracket) {
			stack.pop();
			return;
		}
		if (start instanceof CloseBracket) {
			return;
		}
		System.out.println(stack);
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
			System.out.println("Heloo");
			return;
		}
		if (start instanceof AtTheRate && !start.isFirstConnection()) {
			stack.push(start);
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

	public static Icons getStartIcon(LinkedHashMap<Icons, List<Icons>> adjList) {
		Icons start = null;
		int count = 0;
		for (Map.Entry<Icons, List<Icons>> map : adjList.entrySet()) {
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

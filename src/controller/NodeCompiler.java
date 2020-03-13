package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Connections;
import model.Tab;
import model.TabList;
import view.CloseBracket;
import view.Icons;
import view.OpenBracket;

public class NodeCompiler {

	public static void createAdjacencyList() {

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

		System.out.println(adjList);
		Icons start = getStartIcon(adjList);
		traverse(adjList, start);
	}

	public static void traverse(LinkedHashMap<Icons, List<Icons>> adjList, Icons start) {

		if (start instanceof CloseBracket) {
			System.out.println(start);
			System.out.println("Close");
			return;
		}
		List<Icons> list = adjList.get(start);
		System.out.println(start + "----->>>");
		for (Icons icon : list) {
			
			traverse(adjList, icon);
		}
	}

	public static Icons getStartIcon(LinkedHashMap<Icons, List<Icons>> adjList) {
		Icons start = null;
		for (Map.Entry<Icons, List<Icons>> map : adjList.entrySet()) {
			Icons key = map.getKey();
			if (key instanceof OpenBracket) {
				start = key;
			}
		}
		return start;
	}
}

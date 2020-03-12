package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

import Model.Connections;
import Model.Tab;
import Model.TabList;

public class Workspace extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	public Workspace() {
		this.setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		TabList.getInstance().getTab().setPoint(point);
		TabList.getInstance().getTab().notifyMethod("Dragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (TabList.getInstance().getTab().isFirstDotClicked()) {
			Point point = new Point(e.getX(), e.getY());
			TabList.getInstance().getTab().setDestPoint(point, "DrawTempLine");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		Tab tab = TabList.getInstance().getTab();
		tab.setPoint(point);
		tab.notifyMethod("Clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		TabList.getInstance().getTab().setPoint(point);
		TabList.getInstance().getTab().notifyMethod("Pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		TabList.getInstance().getTab().setSelectedIcon(null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Tab tab = TabList.getInstance().getTab();
		ArrayList<Icons> iconList = tab.getIconList();
		ListIterator<Icons> i = iconList.listIterator();
		while (i.hasNext()) {
			Icons nextIcon = i.next();
			nextIcon.drawShape(graphics);
		}
		for (Connections connection : tab.getConnectionList()) {
			Line2D line = new Line2D.Double();
			line.setLine(connection.getOriginPoint(), connection.getDestPoint());
			Graphics2D g2 = (Graphics2D) graphics;
			g2.draw(line);
		}
	}
}

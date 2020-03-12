package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

import Model.Connections;
import Model.Tab;
import Model.TabList;

public class Workspace extends JPanel implements MouseListener, MouseMotionListener, Serializable {

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
			double originY = connection.getOriginPoint().getY(), destY = connection.getDestPoint().getY();
			if (tab.getOriginIcon() instanceof DoubleBar) {
				originY = originY + 14;
			} else {
				originY = originY + 5;
			}
			if (tab.getDestIcon() instanceof DoubleBar) {
				destY = destY + 14;
			} else {
				destY = destY + 5;
			}
			line.setLine((connection.getOriginPoint().getX() + 5), originY, (connection.getDestPoint().getX() + 5), destY);
			Graphics2D g2 = (Graphics2D) graphics;
			g2.draw(line);
		}
		if (tab.isMoving()) {
			Line2D line = new Line2D.Double();
			double originY = tab.getOriginPoint().getY();
			if (tab.getOriginIcon() instanceof DoubleBar) {
				originY = originY + 14;
			} else {
				originY = originY + 5;
			}
			line.setLine(tab.getOriginPoint().getX() + 5, originY, tab.getDestPoint().getX(), tab.getDestPoint().getY());
			Graphics2D g2 = (Graphics2D) graphics;
			g2.draw(line);
		}
	}
	
	public void setCrossHairCursor() {
		setCursorMethod(Cursor.CROSSHAIR_CURSOR);
	}
	public void setDefaultCursor() {
		setCursorMethod(Cursor.DEFAULT_CURSOR);
	}
	private void setCursorMethod(int cursorType) {
		Cursor cursor = new Cursor(cursorType);
		this.setCursor(cursor);
	}
}

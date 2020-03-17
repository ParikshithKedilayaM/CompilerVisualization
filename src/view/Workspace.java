package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Connections;
import model.Tab;
import model.TabList;

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
		TabList.getInstance().getTab().setPoint(point, "Dragged");
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
		if (e.getClickCount() == 2) {
			tab.setDoubleClick(true);
			tab.setPoint(point, "DoubleClicked");
		} else {
			Timer timer = new Timer(300, new ClickTimer(point));
			timer.setRepeats(false);
			timer.start();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		TabList.getInstance().getTab().setPoint(point, "Pressed");
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
			line.setLine(connection.getOriginPoint().getX() + 5, connection.getOriginPoint().getY() + 5,
					connection.getDestPoint().getX() + 5, connection.getDestPoint().getY() + 5);
			Graphics2D g2 = (Graphics2D) graphics;
			g2.draw(line);
		}
		if (tab.isMoving()) {
			Line2D line = new Line2D.Double();
			line.setLine(tab.getOriginPoint().getX() + 5, tab.getOriginPoint().getY() + 5, tab.getDestPoint().getX(),
					tab.getDestPoint().getY());
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
	
	public String getInputString(String setMessage) {
		return (String) JOptionPane.showInputDialog("Enter description", setMessage);
	}
}

class ClickTimer implements ActionListener {
	private Point point;
	public ClickTimer(Point point) {
		this.point = point;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Tab tab = TabList.getInstance().getTab();
		if (tab.isDoubleClick()) {
			tab.setDoubleClick(false);
		} else {
			tab.setPoint(point, "Clicked");
		}
	}
}

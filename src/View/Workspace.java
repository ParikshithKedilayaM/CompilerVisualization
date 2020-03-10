package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

import Controller.IconFactory;
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

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		IconFactory iconFactory = new IconFactory();
		Icons drawnIcon = iconFactory.drawIcon(point, TabList.getInstance().getTab().getSelectedOption(),
				this.getGraphics());
		if (drawnIcon != null) {
			TabList.getInstance().getTab().addIcon(drawnIcon);
		}
		TabList.getInstance().getTab().notifyMethod("Clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		TabList.getInstance().getTab().setPoint(point);
		TabList.getInstance().getTab().notifyMethod("Clicked");
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
		ArrayList<Icons> iconList = TabList.getInstance().getTab().getIconList();
		ListIterator<Icons> i = iconList.listIterator();
		while (i.hasNext()) {
			Icons nextIcon = i.next();
			nextIcon.drawShape(graphics);
		}
	}
}

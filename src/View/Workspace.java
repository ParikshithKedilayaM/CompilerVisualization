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
import Model.BoxList;

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
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		IconFactory iconFactory = new IconFactory();
		Icons drawnIcon = iconFactory.drawIcon(point, BoxList.getInstance().getBox().getSelectedOption(),
				this.getGraphics());
		if (drawnIcon != null) {	
			BoxList.getInstance().getBox().addIcon(drawnIcon);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

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
		ArrayList<Icons> iconList = BoxList.getInstance().getBox().getIconList();
		ListIterator<Icons> i = iconList.listIterator();
		while(i.hasNext()) {
			Icons nextIcon = i.next();
			nextIcon.drawShape(graphics);
		}
	}
}

package view;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

import javax.swing.JButton;

import model.TabList;

/**
 * 
 * This class holds "Vertical Bar || " icon information and draw them
 * 
 * @author Mayank Kataruka
 * @version 1.0
 */

public class VerticalBar extends JButton implements Serializable {
	private static final long serialVersionUID = 1L;
	private Point point;
	private boolean isInput;

	public VerticalBar(Point point, boolean isInput, Icons icon) {
		this.point = point;
		this.isInput = isInput;
		this.setBorderPainted(false);
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		TabList.getInstance().getTab().getWorkspace().add(this);
		addActionListener(icon);
	}

	public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	public void drawShape() {
		this.setBounds((int) point.getX(), (int) point.getY(), 10, 28);
	}

	public boolean containsPoint(Point point) {
		return this.contains(point);
	}

	public Point getLocation() {
		return point;
	}

	public void setLocation(Point point) {
		this.point = point;
	}

	public void addActionListener(Icons icon) {
		this.addActionListener(new DotBarActionListener(icon, point, isInput));
	}

}

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import javax.swing.JButton;

import model.TabList;

public class Dot implements Serializable {

	private Point point;
	private JButton dot;
	private boolean isInput;
	private RoundButton roundButton;

	public Dot(Point point, boolean isInput, Icons icon) {
		this.point = point;
		this.isInput = isInput;
		roundButton = new RoundButton();
		dot = roundButton;
		drawShape();
		TabList.getInstance().getTab().getWorkspace().add(dot);
		addActionListener(icon);
	}

	public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	public void drawShape() {
		dot.setBounds((int) point.getX(), (int) point.getY(), 10, 10);
	}

	public Point getLocation() {
		return point;
	}

	public void setLocation(Point point) {
		this.point = point;
	}
	
	public void addActionListener(Icons icon) {
		dot.addActionListener(new DotBarActionListener(icon, point, isInput));
	}
}

class RoundButton extends JButton {
	private static final long serialVersionUID = 1L;
	Shape shape;

	public RoundButton() {
		setContentAreaFilled(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(getBackground());
		} else {
			g.setColor(Color.BLACK);
		}

		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
	}

	@Override
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}

		return shape.contains(x, y);
	}
}

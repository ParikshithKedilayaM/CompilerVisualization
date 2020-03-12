package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

import Model.Tab;
import Model.TabList;

public class Dot {

	private static final long serialVersionUID = 1L;
	private Point point;
	private JButton dot;
	private boolean isInput;
	private Icons icon;

	public Dot(Point point, boolean isInput, Icons icon) {
		this.point = point;
		this.isInput = isInput;
		this.icon = icon;
		dot = new RoundButton();
		drawShape();
		TabList.getInstance().getTab().getWorkspace().add(dot);
		dot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tab tab = TabList.getInstance().getTab();
				if (!tab.isFirstDotClicked()) {
					tab.setFirstDotClicked(true);
					tab.setOriginIcon(icon);
					tab.setOriginPoint(point);
					tab.setOriginInput(isInput);
					tab.setOriginDot((JButton) e.getSource());
				} else if (icon != tab.getOriginIcon()) {
					tab.setDestInput(isInput);
					tab.setDestIcon(icon);
					tab.setDestDot((JButton) e.getSource());
					tab.setDestPoint(point, "Drawline");

				}
			}
		});
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

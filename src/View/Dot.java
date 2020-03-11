package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import Model.OptionNames;
import Model.TabList;

public class Dot extends JButton {

	private Point point;
	private JButton dot;
	private boolean isInput;

	public Dot(Point point, boolean isInput) {
		this.point = point;
		this.isInput = isInput;
		dot = new JButton();
		dot.setBounds((int) point.getX(), (int) point.getY(), 10, 10);
		TabList.getInstance().getTab().getWorkspace().add(dot);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

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

	public boolean containsPoint(Point point) {
		return dot.contains(point);
	}

	public Point getLocation() {
		return point;
	}

	public void setLocation(Point point) {
		this.point = point;
	}

}

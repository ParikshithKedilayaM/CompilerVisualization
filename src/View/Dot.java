package View;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Model.TabList;

public class Dot extends JButton {

	private static final long serialVersionUID = 1L;
	private Point point;
	private JButton dot;
	private boolean isInput;
	private Icons icon;

	public Dot(Point point, boolean isInput, Icons icon) {
		this.point = point;
		this.isInput = isInput;
		this.icon = icon;
		dot = new JButton();
		drawShape();
		TabList.getInstance().getTab().getWorkspace().add(dot);
		dot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Dot clicked: "+isInput);
				TabList.getInstance().getTab();
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

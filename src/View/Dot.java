package View;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Model.Tab;
import Model.TabList;

public class Dot extends JButton {

	private static final long serialVersionUID = 1L;
	private Point point;
	private JButton dot;
	private boolean isInput;
	private Icons icon;
	private Dot current;

	public Dot(Point point, boolean isInput, Icons icon) {
		this.current = this;
		this.point = point;
		this.isInput = isInput;
		this.icon = icon;
		dot = new JButton();
		drawShape();
		TabList.getInstance().getTab().getWorkspace().add(dot);
		dot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tab tab = TabList.getInstance().getTab();
				if(!tab.isFirstDotClicked()) {
					tab.setFirstDotClicked(true);
					tab.setOriginIcon(icon);
					tab.setOriginPoint(point);
					tab.setOriginDot((JButton)e.getSource());
				}
				else if(icon != tab.getOriginIcon()) {
					tab.setDestIcon(icon);
					tab.setDestDot((JButton)e.getSource());
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

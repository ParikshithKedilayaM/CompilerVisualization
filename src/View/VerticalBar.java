package View;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Model.Tab;
import Model.TabList;

/**
 * @author Raghavan
 * @version 1.0
 */
public class VerticalBar {

	private Point point;
	private JButton bar;
	private boolean isInput;
	private Icons icon;

	public VerticalBar(Point point, boolean isInput, Icons icon) {
		this.point = point;
		this.isInput = isInput;
		this.icon = icon;
		bar = new JButton();
		bar.setBorderPainted(false);
		bar.setOpaque(true);
		bar.setBackground(Color.BLACK);
		TabList.getInstance().getTab().getWorkspace().add(bar);
		bar.addActionListener(new ActionListener() {
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
		bar.setBounds((int) point.getX(), (int) point.getY(), 10, 28);
	}
	
	
	public boolean containsPoint(Point point) {
		return bar.contains(point);
	}

	public Point getLocation() {
		return point;
	}

	public void setLocation(Point point) {
		this.point = point;
	}

}

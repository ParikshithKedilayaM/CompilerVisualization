package View;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JButton;

import Model.Tab;
import Model.TabList;

/**
 * @author Raghavan
 * @version 1.0
 */
public class VerticalBar extends JButton  {

	private Point point;
	private JButton bar;
	private boolean isInput;
	private Icons icon;

	public VerticalBar(Point point, boolean isInput, Icons icon) {
		this.point = point;
		this.isInput = isInput;
		this.icon = icon;
		bar = new JButton();
		TabList.getInstance().getTab().getWorkspace().add(bar);
		bar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tab tab = TabList.getInstance().getTab();
				
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

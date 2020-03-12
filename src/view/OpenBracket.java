package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class OpenBracket extends Icons {
	private Point point;
	private List<Dot> dots;
	private Shape icon;
	private Point inputPoint, outputPoint;

	public OpenBracket(Point point) {
		dots = new ArrayList<Dot>();
		this.point = point;
//		inputPoint = new Point((int)point.getX() + 10, (int)point.getY()+ 20);
		outputPoint = new Point((int)point.getX() + 80, (int)point.getY()+ 20);
//		dots.add(new Dot(inputPoint, true, this));
		dots.add(new Dot(outputPoint, false, this));
	}

	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
//		inputPoint.setLocation(point.getX() + 10, point.getY() + 20);
		outputPoint.setLocation(point.getX() + 80, point.getY() + 20);
		for (Dot dot:dots) {
			dot.drawShape();
		}
		graphics2.setFont(new Font("Monospaced", Font.BOLD, 32));
		graphics2.drawString("(", (int) point.getX() + 35, (int) point.getY() + 35);
		icon = new Rectangle2D.Double(this.point.getX(), this.point.getY(), 100, 50);
		graphics2.draw(icon);
	}

	@Override
	public boolean containsIcon(Point point) {
		return icon.contains(point);
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return point;
	}

	@Override
	public void setLocation(Point point) {
		// TODO Auto-generated method stub
		this.point = point;
	}

}

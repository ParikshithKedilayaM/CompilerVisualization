package View;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class GreaterThan extends Icons {
	private Point point;
	private Dot input1;
	private Dot input2;
	private Dot output;
	private Shape icon;
	private Point inputPoint1, inputPoint2, outputPoint;

	public GreaterThan(Point point) {
		this.point = point;
		inputPoint1 = new Point((int)point.getX() + 10, (int)point.getY()+ 10);
		inputPoint2 = new Point((int)point.getX() + 10, (int)point.getY()+ 30);
		outputPoint = new Point((int)point.getX() + 80, (int)point.getY()+ 20);
		this.input1 = new Dot(inputPoint1, true, this);
		this.input2 = new Dot(inputPoint2, true, this);
		this.output = new Dot(outputPoint, false, this);
	}

	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		inputPoint1.setLocation(point.getX() + 10, point.getY() + 10);
		inputPoint2.setLocation(point.getX() + 10, point.getY() + 30);
		outputPoint.setLocation(point.getX() + 80, point.getY() + 20);
		input1.drawShape();
		input2.drawShape();
		output.drawShape();
		graphics2.setFont(new Font("Monospaced", Font.BOLD, 32));
		graphics2.drawString(">", (int) point.getX() + 40, (int) point.getY() + 35);
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

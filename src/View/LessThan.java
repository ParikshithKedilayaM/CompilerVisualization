package View;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class LessThan extends Icons {
	private Point point;
	private Dot input;
	private Dot output1;
	private Dot output2;
	private Shape icon;
	
	public LessThan(Point point) {
		this.point = point;
	}

	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		Point inputPoint = new Point();
		inputPoint.setLocation(point.getX() + 10, point.getY() + 20);
		this.input = new Dot(inputPoint, true);
		this.input.drawShape(graphic);
		Point outputPoint1 = new Point();
		outputPoint1.setLocation(point.getX() + 80, point.getY() + 10);
		this.output1 = new Dot(outputPoint1, false);
		this.output1.drawShape(graphic);
		Point outputPoint2 = new Point();
		outputPoint2.setLocation(point.getX() + 80, point.getY() + 30);
		this.output2 = new Dot(outputPoint2, false);
		this.output2.drawShape(graphic);
		graphics2.setFont(new Font("Monospaced", Font.BOLD, 32));
		graphics2.drawString("<", (int)point.getX() + 40, (int)point.getY() + 35);
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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Dot extends Shapes {

	private Point point;
	private Shape circle = null;
	
	public Dot(Point point) {
		this.point = point;
	}

	@Override
	public void drawShape(Graphics graphic) {
		circle = new Ellipse2D.Double(point.getX(), point.getY(), 10, 10);
		Graphics2D graphics2 = (Graphics2D) graphic;
		graphics2.fill(circle);

	}

	public boolean containsPoint(Point point) {
		return circle.contains(point);
	}

	public Point getLocation() {
		return point;
	}

	public void setLocation(Point point) {
		this.point = point;
	}

}

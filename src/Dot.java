import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Dot {

	private Point point;
	private Shape dot;
	private boolean isInput;
	
	public Dot(Point point, boolean isInput) {
		this.point = point;
		this.isInput = isInput;
	}
	
	public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	public void drawShape(Graphics graphic) {
		dot = new Ellipse2D.Double(point.getX(), point.getY(), 10, 10);
		Graphics2D graphics2 = (Graphics2D) graphic;
		graphics2.fill(dot);
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

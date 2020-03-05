import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * @author Raghavan
 * @version 1.0
 */
public class VerticalBar {

	private Point point;
	private Shape bar;
	private boolean isInput;

	public VerticalBar(Point point, boolean isInput) {
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
		bar = new Rectangle2D.Double(point.getX(), point.getY(), 7, 180);
		Graphics2D graphics2 = (Graphics2D) graphic;
		graphics2.fill(bar);

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

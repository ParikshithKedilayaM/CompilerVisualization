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
	private Point inputPoint, outputPoint1, outputPoint2;
	
	public LessThan(Point point) {
		this.point = point;
		inputPoint = new Point((int)point.getX() + 10, (int)point.getY()+ 20);
		outputPoint1 = new Point((int)point.getX() + 80, (int)point.getY()+ 10);
		outputPoint2 = new Point((int)point.getX() + 80, (int)point.getY()+ 30);
		this.input = new Dot(inputPoint, true);
		this.output1 = new Dot(outputPoint1, true);
		this.output2 = new Dot(outputPoint2, false);
	}

	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		inputPoint.setLocation(point.getX() + 10, point.getY() + 20);
		outputPoint1.setLocation(point.getX() + 80, point.getY() + 10);
		outputPoint2.setLocation(point.getX() + 80, point.getY() + 30);
		input.drawShape();
		output1.drawShape();
		output2.drawShape();
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

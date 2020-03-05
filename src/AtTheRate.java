import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class AtTheRate extends Icons {
	private Point point;
	private Dot input1;
	private Dot input2;
	private Dot output1;
	private Dot output2;
	
	AtTheRate(Point point) {
		this.point = point;
	}

	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		Point inputPoint1 = new Point();
		inputPoint1.setLocation(point.getX() + 10, point.getY() + 10);
		this.input1 = new Dot(inputPoint1, true);
		this.input1.drawShape(graphic);
		Point inputPoint2 = new Point();
		inputPoint2.setLocation(point.getX() + 10, point.getY() + 30);
		this.input2 = new Dot(inputPoint2, false);
		this.input2.drawShape(graphic);
		Point outputPoint1 = new Point();
		outputPoint1.setLocation(point.getX() + 80, point.getY() + 10);
		this.output1 = new Dot(outputPoint1, false);
		this.output1.drawShape(graphic);
		Point outputPoint2 = new Point();
		outputPoint2.setLocation(point.getX() + 80, point.getY() + 30);
		this.output2 = new Dot(outputPoint2, false);
		this.output2.drawShape(graphic);
		graphics2.setFont(new Font("Monospaced", Font.BOLD, 32));
		graphics2.drawString("@", (int)point.getX() + 40, (int)point.getY() + 35);
		graphics2.draw(new Rectangle2D.Double(this.point.getX(), this.point.getY(), 100, 50));
	}

	@Override
	public boolean containsPoint(Point point) {
		return containsPoint(point);
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(Point point) {
		// TODO Auto-generated method stub
		
	}

}

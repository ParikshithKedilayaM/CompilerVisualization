import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class OpenBracket extends Icons {
	private Point point;
	private Dot input;
	private Dot output;
	
	OpenBracket(Point point) {
		this.point = point;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		Point inputPoint = new Point();
		inputPoint.setLocation(point.getX() + 80, point.getY() + 10);
		this.input = new Dot(inputPoint, true);
		this.input.drawShape(graphic);
		Point outputPoint = new Point();
		outputPoint.setLocation(point.getX() + 10, point.getY() + 10);
		this.output = new Dot(outputPoint, false);
		this.output.drawShape(graphic);
		graphics2.draw(new Rectangle2D.Double(this.point.getX(), this.point.getY(), 100, 100));
	}

	@Override
	public boolean containsPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
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

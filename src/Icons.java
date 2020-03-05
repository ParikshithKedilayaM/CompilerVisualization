import java.awt.Graphics;
import java.awt.Point;

/**
 *  @author Raghavan
 *  @version 1.0
 */
public abstract class Icons {
	
	public abstract void drawShape(Graphics graphic);
	
	public abstract boolean containsPoint(Point point);
	
	public abstract Point getLocation();
	
	public abstract void setLocation(Point point);
	
	
}
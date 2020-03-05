import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

/**
 *  @author Raghavan
 *  @version 1.0
 */
public abstract class Icons implements MouseListener {
	
	public String description;
	
	public abstract void drawShape(Graphics graphic);
	
	public abstract boolean containsPoint(Point point);
	
	public abstract Point getLocation();
	
	public abstract void setLocation(Point point);
	
	public void mouseClicked(MouseEvent event) {
		if(event.getClickCount() == 2)
			openDialogue();
	}
	
	public void openDialogue() {
		String s = (String)JOptionPane.showInputDialog("Enter description");
	}
}
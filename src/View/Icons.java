package View;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 *  @author Raghavan
 *  @version 1.0
 */
public abstract class Icons extends MouseAdapter {
	
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
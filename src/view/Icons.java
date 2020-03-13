package view;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/**
 * @author Raghavan
 * @version 1.0
 */
public abstract class Icons implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String description = "";

	public abstract void drawShape(Graphics graphic);

	public abstract boolean containsIcon(Point point);

	public abstract Point getLocation();

	public abstract void setLocation(Point point);

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
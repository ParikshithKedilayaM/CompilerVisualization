package Model;
import java.awt.Point;
import java.io.Serializable;

import View.Icons;

/**
 * This class has line information that requires source and destination end
 * point
 * 
 * @author Mayank Kataruka
 * @version 1.0
 */

public class Connections implements Serializable {

	private static final long serialVersionUID = 1L;
	private Point originPoint, destPoint;
	private Icons originIcon, destIcon;

	public Icons getOriginIcon() {
		return originIcon;
	}

	public void setOriginIcon(Icons originShape) {
		this.originIcon = originShape;
	}

	public Icons getDestIcon() {
		return destIcon;
	}

	public void setDestIcon(Icons destShape) {
		this.destIcon = destShape;
	}

	public Point getOriginPoint() {
		return originPoint;
	}

	public void setOriginPoint(Point originPoint) {
		this.originPoint = originPoint;
	}

	public Point getDestPoint() {
		return destPoint;
	}

	public void setDestPoint(Point destPoint) {
		this.destPoint = destPoint;
	}

}

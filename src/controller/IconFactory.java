package controller;

import java.awt.Graphics;
import java.awt.Point;

import model.OptionNames;
import view.AtTheRate;
import view.CloseBracket;
import view.DoubleBar;
import view.GreaterThan;
import view.Hyphen;
import view.Icons;
import view.LessThan;
import view.OpenBracket;

/**
 * This is the factory class used to create and draw different icons on runtime.
 * 
 * @author Mayank Kataruka
 * @version 1.0
 */

public class IconFactory {
	private Icons iconShape;

	/**
	 * This method creates an instance of the selected icon and draws it.
	 * 
	 * @param point
	 * @param icon
	 * @param graphics
	 * @return
	 */
	public Icons drawIcon(Point point, String icon, Graphics graphics) {
		iconShape = null;
		switch (icon) {
		case OptionNames.OPENBRACKET:
			iconShape = new OpenBracket(point);
			break;
		case OptionNames.CLOSEBRACKET:
			iconShape = new CloseBracket(point);
			break;
		case OptionNames.HYPHEN:
			iconShape = new Hyphen(point);
			break;
		case OptionNames.LESSTHAN:
			iconShape = new LessThan(point);
			break;
		case OptionNames.GREATERTHAN:
			iconShape = new GreaterThan(point);
			break;
		case OptionNames.ATTHERATE:
			iconShape = new AtTheRate(point);
			break;
		case OptionNames.BARS:
			iconShape = new DoubleBar(point);
			break;

		}
		if (iconShape != null) {
			iconShape.drawShape(graphics);
		}
		return iconShape;
	}
}

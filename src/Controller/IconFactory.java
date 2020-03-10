package Controller;

import java.awt.Graphics;
import java.awt.Point;

import Model.OptionNames;
import View.AtTheRate;
import View.CloseBracket;
import View.GreaterThan;
import View.Hyphen;
import View.Icons;
import View.LessThan;
import View.OpenBracket;

public class IconFactory {
	public Icons drawIcon(Point point, String icon, Graphics graphics) {
		Icons iconShape = null;
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
			break;

		}
		if (iconShape != null) {
			iconShape.drawShape(graphics);
		}
		return iconShape;
	}
}

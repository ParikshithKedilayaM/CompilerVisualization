import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Workspace extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	public Workspace() {
		this.setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point point = new Point(x, y);
		if (BoxList.getInstance().getBox().getSelectedOption().equals(OptionNames.OPENBRACKET)) {
			OpenBracket openBracket = new OpenBracket(point);
			openBracket.drawShape(this.getGraphics());
		}
		else if (BoxList.getInstance().getBox().getSelectedOption().equals(OptionNames.CLOSEBRACKET)) {
			CloseBracket closeBracket = new CloseBracket(point);
			closeBracket.drawShape(this.getGraphics());
		}
		else if (BoxList.getInstance().getBox().getSelectedOption().equals(OptionNames.HYPHEN)) {
			Hyphen hyphen = new Hyphen(point);
			hyphen.drawShape(this.getGraphics());
		}
		else if (BoxList.getInstance().getBox().getSelectedOption().equals(OptionNames.LESSTHAN)) {
			LessThan lessThan = new LessThan(point);
			lessThan.drawShape(this.getGraphics());
		}
		else if (BoxList.getInstance().getBox().getSelectedOption().equals(OptionNames.GREATERTHAN)) {
			GreaterThan greaterThan = new GreaterThan(point);
			greaterThan.drawShape(this.getGraphics());
		}
		else if (BoxList.getInstance().getBox().getSelectedOption().equals(OptionNames.ATTHERATE)) {
			AtTheRate atTheRate = new AtTheRate(point);
			atTheRate.drawShape(this.getGraphics());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionsPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Box box;
	
	private JButton triangleButton = new JButton();
	private JButton circleButton = new JButton();
	private JButton squareButton = new JButton();
	private final int OFFSET = 50;	
	public OptionsPane() {
		circleButton.setText("Circle");
		//circleButton.addActionListener();
		this.add(circleButton);
		squareButton.setText("Square");
	    this.add(squareButton);
	    triangleButton.setText("Triangle");
	    this.add(triangleButton);
	}

}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionsPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Box box;
	
	private List<JButton> shapes = new ArrayList<>();
	
	private JButton triangleButton;
	private JButton circleButton;
	private JButton squareButton;
	private final int OFFSET = 50;	
	
	public OptionsPane() {
		box = new Box();
		initializeButtons();
		addActionListenersToButtons();
	}
	private void initializeButtons() {
		triangleButton = new JButton("TRIANGLE");
		shapes.add(triangleButton);
		
		squareButton = new JButton("SQUARE");
		shapes.add(squareButton);
		
		circleButton = new JButton("CIRCLE");
		shapes.add(circleButton);
	}
	private void addActionListenersToButtons() {
		ListIterator<JButton> listIterator = shapes.listIterator();
		while (listIterator.hasNext()) {
			JButton button = listIterator.next();
			this.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					box.setSelectedOption(button.getText());
				}
			});
		}
	}
}

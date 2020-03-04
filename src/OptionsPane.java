import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionsPane extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<JButton> shapes = new ArrayList<>();

	public OptionsPane() {
		initializeButtons();
		addActionListenersToButtons();
	}

	private void initializeButtons() {
		JButton triangleButton = new JButton(OptionNames.TRIANGLE);
		shapes.add(triangleButton);

		JButton squareButton = new JButton(OptionNames.SQUARE);
		shapes.add(squareButton);

		JButton circleButton = new JButton(OptionNames.CIRCLE);
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
					Box.getBoxInstance().setSelectedOption(button.getText());
				}
			});
		}
	}
}

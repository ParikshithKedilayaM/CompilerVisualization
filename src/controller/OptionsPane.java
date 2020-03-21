package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.OptionNames;
import model.TabList;

/**
 * This is the class where all the JButtons with images of icons on it is created on the left Panel.
 * 
 * @author Rishika Bera
 * @version 1.0
 */
public class OptionsPane extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<JButton> shapes = new ArrayList<>();

	public OptionsPane() {
		initializeButtons();
		addActionListenersToButtons();
	}

	private void initializeButtons() {
		JButton openBracket = new JButton(new ImageIcon("Resources//openBracket.png"));
		openBracket.setName(OptionNames.OPENBRACKET);
		shapes.add(openBracket);

		JButton closeBracket = new JButton(new ImageIcon("Resources//closeBracket.png"));
		closeBracket.setName(OptionNames.CLOSEBRACKET);
		shapes.add(closeBracket);
		
		JButton lessThan = new JButton(new ImageIcon("Resources//lessThan.png"));
		lessThan.setName(OptionNames.LESSTHAN);
		shapes.add(lessThan);

		JButton greaterThan = new JButton(new ImageIcon("Resources//greaterThan.png"));
		greaterThan.setName(OptionNames.GREATERTHAN);
		shapes.add(greaterThan);

		JButton atTheRate = new JButton(new ImageIcon("Resources//atTheRate.png"));
		atTheRate.setName(OptionNames.ATTHERATE);
		shapes.add(atTheRate);
		
		JButton hyphen = new JButton(new ImageIcon("Resources//hyphen.png"));
		hyphen.setName(OptionNames.HYPHEN);
		shapes.add(hyphen);
		
		JButton bars = new JButton(new ImageIcon("Resources//bars.png")) ;
		bars.setName(OptionNames.BARS);
		shapes.add(bars);
	}

	private void addActionListenersToButtons() {
		ListIterator<JButton> listIterator = shapes.listIterator();
		Box box = Box.createVerticalBox();
		while (listIterator.hasNext()) {
			JButton button = listIterator.next();
			box.add(Box.createVerticalStrut(10));
			box.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TabList.getInstance().getTab().setSelectedOption(button.getName());
				}
			});
		}
		this.add(box);
	}
}

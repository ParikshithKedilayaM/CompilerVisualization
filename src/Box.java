
public class Box {
	private static Box box;
	private String selectedOption;
	public static Box getBoxInstance() {
		if(box == null) {
			box = new Box();
		}
		return box;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		System.out.println(selectedOption);
		this.selectedOption = selectedOption;
	}
}


public class Box {
	private static Box box;
	
	public static Box getBoxInstance() {
		if(box == null) {
			box = new Box();
		}
		return box;
	}
}

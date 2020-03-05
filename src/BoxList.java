import java.util.ArrayList;
import java.util.List;

public class BoxList {
	private int currentTabIndex;
	private List<Box> boxList;
	private static BoxList boxListInstance;
	
	private BoxList() {
		currentTabIndex = 0;
		boxList = new ArrayList<Box>();
	}
	
	public static BoxList getInstance() {
		if(boxListInstance == null) {
			boxListInstance = new BoxList();
		}
		return boxListInstance;
	}
	public Box getBox() {
		return boxList.get(currentTabIndex);
	}
	public void addBox() {
		boxList.add(new Box());
	}

	public int getCurrentTabIndex() {
		return currentTabIndex;
	}

	public void setCurrentTabIndex(int currentTabIndex) {
		this.currentTabIndex = currentTabIndex;
	}
	public int getSize() {
		return boxList.size();
	}
}

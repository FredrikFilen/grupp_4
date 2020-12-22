package grupp4;

import java.util.ArrayList;
import java.util.Collections;

public class SkierSorter {
	ArrayList<Skier> skierlist = new ArrayList<Skier>();

	public SkierSorter(ArrayList<Skier> skierTable) {
		this.skierlist = skierTable;
	}

	public ArrayList<Skier> getSortedSkierListByTime() {
		Collections.sort(skierlist);
		return skierlist;
	}
}

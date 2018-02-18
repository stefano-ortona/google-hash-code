package google.com.ortona.hashcode.pizza.logic.comparator;

import java.util.Comparator;

import google.com.ortona.hashcode.pizza.model.Slice;

public class SliceDimensionComparator implements Comparator<Slice> {

	@Override
	public int compare(Slice o1, Slice o2) {
		return o1.dimen() - o2.dimen();
	}

}

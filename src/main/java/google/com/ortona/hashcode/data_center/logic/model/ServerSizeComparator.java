package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

public class ServerSizeComparator implements Comparator<Server>{
	@Override
	public int compare(Server o1, Server o2) {
		if (o1.getSize() != o2.getSize()) {
			return o1.getSize() - o2.getSize();
		}
		if (o1.getCapacity() != o2.getCapacity()) {
			return o2.getCapacity() - o1.getCapacity();
		}
		return o1.id - o2.id;

	}

	public static void main(String[] args) {
		final Server s1 = new Server(0, 10, 2);
		final Server s2 = new Server(1, 10, 1);
		final List<Server> lists = Lists.newArrayList();
		lists.add(s1);
		lists.add(s2);
		Collections.sort(lists, new ServerComparator());
		System.out.println(lists);
	}

}

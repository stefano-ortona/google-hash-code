package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

public class ServerRandomComparator implements Comparator<Server> {
	Random r = new Random();

	@Override
	public int compare(Server o1, Server o2) {
		final int nextInt = r.nextInt(2);
		return nextInt == 0 ? -1 : 1;
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

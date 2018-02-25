package google.com.ortona.hashcode.data_center.logic.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

public class ServerWeightedAverageComparator implements Comparator<Server>{
	private final double CAP_WEIGHT = 0.1;
	private final double SIZE_WEIGHT = 0.9;

	@Override
	public int compare(Server o1, Server o2) {
		//first better capacity and size
		if(o1.getCapacity() > o2.getCapacity() && o1.getSize() < o2.getSize()){
			return -1;
		}
		
		//second better capacity and size
		if(o2.getCapacity() > o1.getCapacity() && o2.getSize() < o1.getSize()){
			return 1;
		}
		
		//mixed features
		double firstWeightedValue = o1.getCapacity()*CAP_WEIGHT -o1.getSize()*SIZE_WEIGHT;
		double secondWeightedValue = o2.getCapacity()*CAP_WEIGHT -o2.getSize()*SIZE_WEIGHT;
		
		if(firstWeightedValue > secondWeightedValue){
			return -1;
		}
		
		if(secondWeightedValue > firstWeightedValue){
			return 1;
		}
		
		//same feature
		return o1.getId()-o2.getId();

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

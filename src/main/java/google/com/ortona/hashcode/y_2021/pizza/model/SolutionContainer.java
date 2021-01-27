package google.com.ortona.hashcode.y_2021.pizza.model;

import java.util.ArrayList;
import java.util.List;

import google.com.ortona.hashcode.qualification_2017.model.Cache;

public class SolutionContainer {

    private List<TeamAllocation> allocationList = new ArrayList<>();

    public SolutionContainer() {
    }

	public List<TeamAllocation> getAllocationList() {
		return allocationList;
	}

	public void setAllocationList(List<TeamAllocation> allocationList) {
		this.allocationList = allocationList;
	}

	@Override
    public String toString() {
		String caches = "";
		caches += allocationList.size();
		for (final TeamAllocation ta : getAllocationList()) {
			caches += "\n" + ta.toString();
		}
		return caches;
    }

    public int getScore() {
        return allocationList.stream().mapToInt(TeamAllocation::getScore).sum();
    }
}

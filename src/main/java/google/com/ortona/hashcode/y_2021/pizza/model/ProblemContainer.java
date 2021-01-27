package google.com.ortona.hashcode.y_2021.pizza.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemContainer {

	private Map<Integer, Integer> teamMap = new HashMap<>();

	private List<Pizza> pizzaList = new ArrayList<>();

	public ProblemContainer(Map<Integer, Integer> teamMap, List<Pizza> pizzaList) {
		this.teamMap = teamMap;
		this.pizzaList = pizzaList;
	}

	public Map<Integer, Integer> getTeamMap() {
		return teamMap;
	}

	public void setTeamMap(Map<Integer, Integer> teamMap) {
		this.teamMap = teamMap;
	}

	public List<Pizza> getPizzaList() {
		return pizzaList;
	}

	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}

	@Override
	public String toString() {
		return "ProblemContainer{" +
				"teamMap=" + teamMap +
				", pizzaList=" + pizzaList +
				'}';
	}
}

package google.com.ortona.hashcode.y_2022.pizza.model;

import java.util.List;


public class SolutionContainer {
	
	public List<String> finalIngredients;
	public int score;
	
	
	public int getScore() {
		return score;
	}
	
	public String toString() {
		return finalIngredients.size() + " "  + String.join(" ",finalIngredients);
	}
}
